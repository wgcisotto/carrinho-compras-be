package com.altran.controllers;

import com.altran.models.Item;
import com.altran.models.User;
import com.altran.repositories.UserRepository;
import com.altran.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    private static User user;

    @BeforeClass
    public static void init(){
        user = User.builder()
                .name("item 1")
                .email("teste@teste.com")
                .build();
    }

    @Test
    public void getItems_thenReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void save_thenReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void update_thenReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/users/5d9eb050b1746f1d188c8e55")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void show_thenReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/5d9eb050b1746f1d188c8e55")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
