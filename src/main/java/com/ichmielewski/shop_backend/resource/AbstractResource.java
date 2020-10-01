package com.ichmielewski.shop_backend.resource;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.dto.AuditList;
import com.ichmielewski.shop_backend.entity.AbstractEntity;
import com.ichmielewski.shop_backend.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public abstract class AbstractResource<ENTITY extends AbstractEntity, DTO extends AbstractDTO> {

    public abstract AbstractService<ENTITY, DTO> getService();

    private static final Logger log = LoggerFactory.getLogger(AbstractResource.class);


    @GetMapping(value = "/open/{id}")
    @PreAuthorize("hasAuthority('view')")
    public DTO getById(@PathVariable("id") Long id) {
        return getService().findById(id);
    }


    @GetMapping("open/")
    @PreAuthorize("hasAuthority('view')")
    public List<DTO> getAll(@RequestParam(value = "search", required = false) Pageable pageable) {
        return getService().getAll();
    }


    @PostMapping
    @PreAuthorize("hasAuthority('edit')")
    public DTO create(@RequestBody @Valid DTO dto) {
        return getService().save(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('edit')")
    public DTO update(@PathVariable("id") Long id, @RequestBody @Valid DTO dto) {
        return getService().update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('edit')")
    public void delete(@PathVariable("id") Long id) throws Exception {
        getService().delete(id);
    }

    @GetMapping("/auditList/{rev}")
    @PreAuthorize("hasAuthority('view')")
    public AuditList<DTO> getAuditList(@PathVariable(name = "rev") Integer rev) {
        return getService().getAuditList(rev);
    }

}
