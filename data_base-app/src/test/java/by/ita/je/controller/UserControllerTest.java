package by.ita.je.controller;

import by.ita.je.dao.UserDao;
import by.ita.je.dto.UserDto;
import by.ita.je.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {/*


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
                .login("pave")
                .pasword(13)
                .build();
        userDao.save(user1);
        userDao.save(user2);
List<User> users = userDao.findAll();
List <UserDto> list =users.stream()
                .map(user->objectMapper.convertValue(user, UserDto.class))
                        .collect(Collectors.toList());
        mockMvc.perform(
                (get("/user/read/all"))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json((objectMapper.writeValueAsString(list))));


    }

*/
}