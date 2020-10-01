package com.ichmielewski.shop_backend.service;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.dto.AuditList;
import com.ichmielewski.shop_backend.entity.AbstractEntity;

import java.util.List;


public interface AbstractService<ENTITY extends AbstractEntity, DTO extends AbstractDTO> {

    DTO update(Long id, DTO dto);

    DTO findById(Long id);

    DTO save(DTO entity);

    void delete(Long id);

    List<DTO> getAll();

    AuditList<DTO> getAuditList(Integer rev);

}
