package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.dto.AuditList;
import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import com.ichmielewski.shop_backend.entity.AbstractEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.service.AbstractService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;


public abstract class AbstractServiceImpl<ENTITY extends AbstractEntity, DTO extends AbstractDTO> implements AbstractService<ENTITY, DTO> {

    private final AbstractRepository<ENTITY> repository;

    private final AbstractMapper<ENTITY, DTO> mapper;

    private final EntityManager entityManager;

    public AbstractServiceImpl(AbstractRepository<ENTITY> repository, AbstractMapper<ENTITY, DTO> mapper, EntityManager entityManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public DTO update(Long id, DTO dto) {
        dto.setId(id);
        ENTITY entity = mapper.mapToEntity(dto);
        return mapper.mapToDto(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findById(Long id) {
        ENTITY entity = repository.getOne(id);
        return mapper.mapToDto(entity);
    }

    @Override
    @Transactional
    public DTO save(DTO dto) {
        ENTITY entity = mapper.mapToEntity(dto);
        return mapper.mapToDto(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);

    }


    @Transactional(readOnly = true)
    public List<DTO> getAll() {
        return mapper.mapToDtos(repository.findAll());
    }


    @Override
    public AuditList<DTO> getAuditList(Integer revision) {
        AuditList<DTO> auditList = findRevisions(revision);
        optimizeAuditList(auditList);

        return auditList;
    }

    @Transactional
    public AuditList<DTO> findRevisions(Integer revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        List<ENTITY> modified = auditReader
                .createQuery().forRevisionsOfEntity(getEntityClass(), true, false)
                .add(AuditEntity.revisionNumber().gt(revision))
                .addOrder(AuditEntity.revisionNumber().asc())
                .getResultList();

        List<Long> deleted = auditReader.createQuery().forRevisionsOfEntity(getEntityClass(), true)
                .add(AuditEntity.revisionType().eq(RevisionType.DEL))
                .add(AuditEntity.revisionNumber().gt(revision))
                .addProjection(AuditEntity.id())
                .getResultList();

        Integer lastRevision = (Integer) auditReader.createQuery().forRevisionsOfEntity(getEntityClass(), true)
                .addProjection(AuditEntity.revisionNumber().max())
                .getSingleResult();


        AuditList<DTO> auditList = new AuditList<>();
        auditList.setModified(mapper.mapToDtos(modified));
        auditList.setDeleted(deleted);
        auditList.setLastRevision(lastRevision);
        return auditList;
    }

    private void optimizeAuditList(AuditList<DTO> auditList) {
        HashSet<Long> toDelete = new HashSet<>(auditList.getDeleted());
        List<DTO> modified = auditList.getModified();

        for (int i = modified.size()-1; i >= 0 ; i--) {
            Long id = modified.get(i).getId();
            if(toDelete.contains(id)) {
                modified.remove(i);
            }
            else {
                toDelete.add(id);
            }
        }
    }
    protected abstract Class<? extends AbstractEntity> getEntityClass();
}


