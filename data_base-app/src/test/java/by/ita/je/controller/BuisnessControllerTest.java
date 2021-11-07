package by.ita.je.controller;

import by.ita.je.dao.AnnouncementDao;
import by.ita.je.dao.BestAnnouncementDao;
import by.ita.je.dao.UserDao;
import by.ita.je.entity.*;
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
   private BestAnnouncementDao bestAnnouncementDao;
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
        User user = User.builder()
                .login("eee")
                .pasword(222)
                .build();
        userDao.save(user);
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
        Coment coment = Coment.builder()
                .message("ok")
                .build();
        Announcement announcement = Announcement.builder()
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        mockMvc.perform(
                        post("/buisness/create?id=1")
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
        User user = User.builder()
                .login("eee")
                .pasword(222)
                .build();
        userDao.save(user);
        Car car = Car.builder()
                .nameCar("www")
                .modelCar("eee")
                .build();
        Coment coment = Coment.builder()
                .message("ok")
                .build();
        Announcement announcement = Announcement.builder()
                .id(1)
                .get_up(1)
                .user(user)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        announcementDao.save(announcement);
        mockMvc.perform(
                        delete("/buisness/delete?id=5" ))
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
        Announcement announcement = Announcement.builder()
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        announcementDao.save(announcement);

        Announcement announcementUpdate = Announcement.builder()
                .get_up(1)
                .numberPhone(444)
                .car(car)
                .coment(coment)
                .build();
        mockMvc.perform(
                        put("/buisness/updateAnnouncement?id=2" )
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
        User user1 = User.builder()
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
        Announcement announcement = Announcement.builder()
                .user(user1)
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        announcementDao.save(announcement);
        mockMvc.perform(
                        get("/buisness/readall/3" )
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());

    }



    @SneakyThrows
    @Test
    void addZakladka() {
        User user1 = User.builder()
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
        Announcement announcement1 = Announcement.builder()
                .user(user1)
                .get_up(1)
                .numberPhone(121)
                .car(car)
                .coment(coment)
                .build();
        announcementDao.save(announcement1);
        BestAnnouncement bestAnnouncement = BestAnnouncement.builder()
                .announcement(announcement1)
                .user(user1)
                .build();

        mockMvc.perform(
                        post("/buisness/add/best?id=3&userId=2" )
                                //.content(objectMapper.writeValueAsString(bestAnnouncement))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());

    }


    /*@SneakyThrows
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
        Announcement announcement = Announcement.builder()
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
    }*/
}