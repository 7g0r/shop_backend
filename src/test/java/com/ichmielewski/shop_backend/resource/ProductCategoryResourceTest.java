package com.ichmielewski.shop_backend.resource;

import com.ichmielewski.shop_backend.dto.ProductCategoryDTO;
import com.ichmielewski.shop_backend.service.ProductCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
@SpringBootTest
@AutoConfigureMockMvc
public class ProductCategoryResourceTest {


    private MockMvc mockMvc;

    private ProductCategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        categoryService = mock(ProductCategoryService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductCategoryResource(categoryService))
                .build();

    }

    @Test
    public void getById_returnsDto() throws Exception {
        ProductCategoryDTO categoryDTO = new ProductCategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Pikachu");
        when(categoryService.findById(1L)).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/product-categories/open/1"))
                .andExpect(status().isOk())
                .andExpect(content().xml("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<ProductCategory>\n" +
                        "    <id>1</id>\n" +
                        "    <name>Pikachu</name>\n" +
                                "</ProductCategory>"
                        ));
    }

    @Test
    public void getAll_returnsListDto() throws Exception {
        ProductCategoryDTO categoryDTO1 = new ProductCategoryDTO();
        ProductCategoryDTO categoryDTO2 = new ProductCategoryDTO();
        List<ProductCategoryDTO> categoryDTOS =  asList(categoryDTO1, categoryDTO2);
        categoryDTO1.setId(1L);
        categoryDTO1.setName("Pikachu");
        categoryDTO2.setId(2L);
        categoryDTO2.setName("Bulbasaur");
        when(categoryService.getAll()).thenReturn(categoryDTOS);

        mockMvc.perform(get("/api/product-categories/open/"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[\n" +
                                "  {\n" +
                                "    \"id\": 1,\n" +
                                "    \"name\": \"Pikachu\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"id\": 2,\n" +
                                "    \"name\": \"Bulbasaur\"\n" +
                                "  }\n" +
                                "]"));
    }
}
