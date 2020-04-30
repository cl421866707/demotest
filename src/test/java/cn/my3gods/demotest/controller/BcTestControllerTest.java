package cn.my3gods.demotest.controller;

import javax.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/30 15:12
 */
@SpringBootTest
@AutoConfigureMockMvc
class BcTestControllerTest {

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.err.println("----------------------------------------------begin----------------------------------------------");
    }

    @AfterEach
    void tearDown() {
        System.err.println("----------------------------------------------end----------------------------------------------");
    }

    @Test
    void getBrandsWithoutCondition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bigcommerce/brands")).andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getProductsWithoutCondition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bigcommerce/products")).andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
}