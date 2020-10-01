package com.ichmielewski.shop_backend.service.impl;


import com.ichmielewski.shop_backend.dto.WarehouseDTO;
import com.ichmielewski.shop_backend.entity.AbstractEntity;
import com.ichmielewski.shop_backend.entity.WarehouseEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.mappers.WarehouseMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.repository.WarehouseRepository;
import com.ichmielewski.shop_backend.service.WarehouseService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class WarehouseServiceImpl extends AbstractServiceImpl<WarehouseEntity, WarehouseDTO> implements WarehouseService {


    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;


    public WarehouseServiceImpl(EntityManager entityManager,
                                WarehouseRepository warehouseRepository,
                                WarehouseMapper warehouseMapper) {
        super(warehouseRepository, warehouseMapper, entityManager);
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    protected Class<? extends AbstractEntity> getEntityClass() {
        return WarehouseEntity.class;
    }
}

