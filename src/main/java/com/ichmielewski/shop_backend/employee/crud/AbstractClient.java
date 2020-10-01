package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface AbstractClient<DTO extends AbstractDTO> {


    @GetMapping
    List<DTO> getAll();

    @GetMapping("/{id}")
    DTO getById(@PathVariable(name = "id") Long id);

    @PostMapping
    DTO add(@Valid @RequestBody DTO dto);

    @PutMapping("/{id}")
    DTO update(@PathVariable(name = "id") Long id, @Valid @RequestBody DTO dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id);


}
