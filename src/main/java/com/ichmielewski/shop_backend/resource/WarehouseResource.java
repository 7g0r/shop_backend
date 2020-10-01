package com.ichmielewski.shop_backend.resource;

import com.ichmielewski.shop_backend.dto.WarehouseDTO;
import com.ichmielewski.shop_backend.entity.WarehouseEntity;
import com.ichmielewski.shop_backend.service.AbstractService;
import com.ichmielewski.shop_backend.service.WarehouseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/stocks")
public class WarehouseResource extends AbstractResource<WarehouseEntity, WarehouseDTO> {

    private final WarehouseService warehouseService;
    private final Logger log = LoggerFactory.getLogger(WarehouseResource.class);

    public WarehouseResource(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public AbstractService<WarehouseEntity, WarehouseDTO> getService() {
        return warehouseService;
    }
}
