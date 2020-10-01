package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.WarehouseDTO;
import com.ichmielewski.shop_backend.entity.WarehouseEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.mappers.WarehouseMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.repository.WarehouseRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class WarehouseServiceImplTest extends AbstractServiceImplTest<WarehouseEntity, WarehouseDTO> {

    @InjectMocks
    private WarehouseServiceImpl warehouseService;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private WarehouseMapper warehouseMapper;

    @Override
    AbstractServiceImpl<WarehouseEntity, WarehouseDTO> getService() {
        return warehouseService;
    }

    @Override
    AbstractRepository<WarehouseEntity> getRepository() {
        return warehouseRepository;
    }

    @Override
    AbstractMapper<WarehouseEntity, WarehouseDTO> getMapper() {
        return warehouseMapper;
    }

    @Override
    Class<WarehouseDTO> getDtoClass() {
        return WarehouseDTO.class;
    }

    @Override
    Class<WarehouseEntity> getEntityClass() {
        return WarehouseEntity.class;
    }
}
