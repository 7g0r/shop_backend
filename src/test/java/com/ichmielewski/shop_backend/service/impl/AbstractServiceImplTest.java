package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.entity.AbstractEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractServiceImplTest<ENTITY extends AbstractEntity, DTO extends AbstractDTO> {


    abstract AbstractServiceImpl<ENTITY, DTO> getService();

    abstract AbstractRepository<ENTITY> getRepository();

    abstract AbstractMapper<ENTITY, DTO> getMapper();

    @Mock
    EntityManager entityManager;

    abstract Class<DTO> getDtoClass();

    abstract Class<ENTITY> getEntityClass();
    @Captor
    ArgumentCaptor<DTO> dtoCaptor;

    @Test
    public void update_savesUpdatedProductAndReturnsDto() {
        DTO inputDto = mock(getDtoClass());
        DTO returnedDto = mock(getDtoClass());
        ENTITY savedEntity = mock(getEntityClass());
        when(getMapper().mapToEntity(inputDto)).thenReturn(savedEntity);
        when(getRepository().save(savedEntity)).thenReturn(savedEntity);
        when(getMapper().mapToDto(savedEntity)).thenReturn(returnedDto);


        DTO result = getService().update(12L, inputDto);
        verify(inputDto).setId(12L);
        verify(getMapper()).mapToEntity(dtoCaptor.capture());
        verify(getRepository()).save(savedEntity);
        verify(getMapper()).mapToDto(savedEntity);
        assertThat(result).isEqualTo(returnedDto);
    }

    @Test
    public void save_savesAndReturnsMappedEntity() {
        DTO inputDto = mock(getDtoClass());
        DTO returnedDto = mock(getDtoClass());
        ENTITY mappedEntity = mock(getEntityClass());
        ENTITY savedEntity = mock(getEntityClass());
        when(getMapper().mapToEntity(inputDto)).thenReturn(mappedEntity);
        when(getRepository().save(mappedEntity)).thenReturn(savedEntity);
        when(getMapper().mapToDto(savedEntity)).thenReturn(returnedDto);

        DTO result = getService().save(inputDto);

        verify(getMapper()).mapToEntity(inputDto);
        verify(getRepository()).save(mappedEntity);
        verify(getMapper()).mapToDto(savedEntity);
        assertThat(result).isEqualTo(returnedDto);
    }

    @Test
    public void delete_deletesById() {
        getRepository().deleteById(1L);

        verify(getRepository()).deleteById(1L);
    }

    @Test
    public void findById_findsByIdEntityAndReturnsDto() {
        DTO mappedDto = mock(getDtoClass());
        ENTITY returnedEntity = mock(getEntityClass());
        when(getRepository().getOne(1L)).thenReturn(returnedEntity);
        when(getMapper().mapToDto(returnedEntity)).thenReturn(mappedDto);

        DTO result = getService().findById(1L);

        verify(getRepository()).getOne(1L);
        verify(getMapper()).mapToDto(returnedEntity);
        assertThat(result).isEqualTo(mappedDto);
    }

    @Test
    public void getAll_getAllEntityAndReturnDto() {
        List<ENTITY> returnedEntity = asList(
                mock(getEntityClass()),
                mock(getEntityClass()));
        List<DTO> returnedDtos = asList(
                mock(getDtoClass()),
                mock(getDtoClass()));
        when(getRepository().findAll()).thenReturn(returnedEntity);
        when(getMapper().mapToDtos(returnedEntity)).thenReturn(returnedDtos);

        List<DTO> result = getService().getAll();

        verify(getRepository()).findAll();
        verify(getMapper()).mapToDtos(returnedEntity);
        assertThat(result).isEqualTo(returnedDtos);
    }
}
