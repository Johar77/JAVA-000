package com.johar.geektime.redisdistributedlock.controller;

import com.alibaba.fastjson.JSONObject;
import com.johar.geektime.redisdistributedlock.dto.OpCode;
import com.johar.geektime.redisdistributedlock.dto.OrderParamDto;
import org.databene.contiperf.PerfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Random;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @RepeatedTest(10)
    void addOrderTest() throws Exception {
        Random random = new Random();
        OrderParamDto paramDto = new OrderParamDto();
        paramDto.setProductId(random.nextInt(5) + 1);
        paramDto.setType(randomEnum());
        paramDto.setNum(random.nextInt(100) + 1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(paramDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    private OpCode randomEnum(){
        Random random = new Random();
        int num = random.nextInt(OpCode.values().length - 1);
        return OpCode.values()[num];
    }
}