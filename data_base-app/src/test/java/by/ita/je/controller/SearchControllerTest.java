package by.ita.je.controller;

import by.ita.je.dao.AnnouncementDao;
import by.ita.je.dao.SearchDao;
import by.ita.je.dto.SearchDto;
import by.ita.je.module.Announcement;
import by.ita.je.module.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SearchDao searchDao;
    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private MockMvc mockMvc;


    @SneakyThrows
    @Test
    void readByCriteria() throws JsonProcessingException {
        Car car = Car.builder()
                .nameCar("gaz")
                .modelCar("ww")
                .build();
Announcement announcement = Announcement.builder()
        .numberPhone(223)
        .get_up(5)
        .car(car)
        .build();
        announcementDao.save(announcement);
        List<Announcement> announcementList = new ArrayList<>();
        announcementList.add(announcement);
        SearchDto searchDto = SearchDto.builder()
                .nameCar("gaz")
                .modelCar("ww")
                .build();

        mockMvc.perform(
                post("/search/criteria")
                        .content(objectMapper.writeValueAsString(searchDto))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());



    }
};