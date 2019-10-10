package com.altran.controllers;

import com.altran.models.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static Item item;

    @BeforeClass
    public static void init(){
        item = Item.builder()
                .name("item 1")
                .price(10L)
                .build();
    }

    @Test
    public void getByUserId_thenReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void addItem_thenReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/carts/user/5d9eb050b1746f1d188c8e55")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(item))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void removeItem_thenReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/carts/5d9eb050b1746f1d188c8e55")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(item))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Ignore
    public void deleteCart_thenReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/carts/5d9eb050b1746f1d188c8e55")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
