package by.ita.je.controller;

import by.ita.je.dao.UserDao;
import by.ita.je.module.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        userDao.deleteAll();
    }
    @SneakyThrows
    @Test
    void createUser() {

        User user =  User.builder()
                .login("pavel")
                .pasword(123)
                .build();
        mockMvc.perform(
                post("/user/create")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
               .andExpect(MockMvcResultMatchers.jsonPath("$.user_id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("pavel"));

    }

    @SneakyThrows
    @Test
    void readAll() {
        User user1 =  User.builder()
                .login("pavel")
                .pasword(123)
                .build();
        User user2 =  User.builder()
                .login("pavel")
                .pasword(123)
                .build();
        userDao.save(user1);
        userDao.save(user2);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/user/read/all")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json((objectMapper.writeValueAsString(Arrays.asList(user1,user2)))));


    }
}