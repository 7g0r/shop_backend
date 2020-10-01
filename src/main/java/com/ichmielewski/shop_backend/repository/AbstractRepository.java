package com.ichmielewski.shop_backend.repository;

import com.ichmielewski.shop_backend.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@NoRepositoryBean
@RepositoryRestResource
public interface AbstractRepository<ENTITY extends AbstractEntity> extends JpaRepository <ENTITY, Long>, JpaSpecificationExecutor<ENTITY> {
}
