package by.ita.je.controller;

import by.ita.je.dao.AnnouncementDao;
import by.ita.je.dao.UserDao;
import by.ita.je.module.Announcement;
import by.ita.je.module.Car;
import by.ita.je.module.Coment;
import by.ita.je.module.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BuisnessControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        announcementDao.deleteAll();
        userDao.deleteAll();
    }
    @SneakyThrows
    @Test
    void createAnnouncement() {
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
      Announcement announcement  =  Announcement.builder()
              .get_up(1)
                .numberPhone(121)
              .car(car)
                .build();
        mockMvc.perform(
                post("/buisness/create")
                        .content(objectMapper.writeValueAsString(announcement))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberPhone").value("121"));

    }

    @Test
    void readOne() {

    }

    @SneakyThrows
    @Test
    void deleteById() {
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
        Announcement announcement  =  Announcement.builder()
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .build();
        announcementDao.save(announcement );
        mockMvc.perform(
                delete("/buisness/delete?id="+"1"))
                .andExpect(status().isOk());

    }

    @SneakyThrows
    @Test
    void update() {
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
        Coment coment = Coment.builder()
                .message("aaa")
                .build();
        Announcement announcement  =  Announcement.builder()
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        announcementDao.save(announcement );

        Announcement announcementUpdate  =  Announcement.builder()
                .get_up(1)
                .numberPhone(444)
                .car(car)
                .coment(coment)
                .build();
        mockMvc.perform(
                put("/buisness/update?id="+1L)
                        .content(objectMapper.writeValueAsString(announcementUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberPhone").value("444"));
    }

    @SneakyThrows
    @Test
    void readAll() {
        User user1 =  User.builder()
                .login("pavel")
                .pasword(123)
                .build();
        userDao.save(user1);
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
        Coment coment = Coment.builder()
                .message("aaa")
                .build();
        Announcement announcement  =  Announcement.builder()
                .user(user1)
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        announcementDao.save(announcement );
        mockMvc.perform(
               get("/buisness/readall/"+"1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());
               // .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(Arrays.asList(announcement)))));




    }

    @SneakyThrows
    @Test
    void coment() {

        Coment coment = Coment.builder()
                .message("aaa")
                .build();
        Coment coment1 = Coment.builder()
                .message("")
                .build();
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();

        Announcement announcement  =  Announcement.builder()
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment1)
                .build();
        announcementDao.save(announcement );
        mockMvc.perform(
                post("/buisness/update/coment?id="+"1")
                        .content(objectMapper.writeValueAsString(coment))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("aaa"));
    }

    @SneakyThrows
    @Test
    void addZakladka() {
        User user =  User.builder()
                .login("pavel")
                .pasword(123)
                .build();
        userDao.save(user);
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
        Coment coment = Coment.builder()
                .message("aaa")
                .build();
        Announcement announcement  =  Announcement.builder()
                .id(1)
                .user(user)
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        announcementDao.save(announcement );
        mockMvc.perform(
                post("/buisness/add/best?id="+"1")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());
               // .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("aaa"));
    }

    @Test
    void createCreditcart() {
    }

    @Test
    void addBalance() {
    }

    @SneakyThrows
    @Test
    void getup() throws JsonProcessingException {
        User user = User.builder()
                .login("eee")
                .balance(20)
                .pasword(123)
                .build();
        userDao.save(user);
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
        Announcement announcement  =  Announcement.builder()
                .get_up(1)
                .numberPhone(121)
                .user(user)
                .car(car)
                .build();
        announcementDao.save(announcement);
        mockMvc.perform(
                put("/buisness/getup")
                        .content(objectMapper.writeValueAsString(announcement))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.get_up").value("2"));
    }
}