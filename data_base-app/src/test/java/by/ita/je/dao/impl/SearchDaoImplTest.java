package by.ita.je.dao.impl;

import by.ita.je.dao.SearchDao;
import by.ita.je.entity.Announcement;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SearchDaoImplTest {
    @Autowired
    private SearchDao searchDao;
    String nameCar = "opel";
    String modelCar = "astra";
    int price=1000;
    int yearOfIssue=2000;
    int milage=1000000;
    int volumeEngine=2;
    String typeEngine="dt";
    String transmission="avto";
    String location="Brest";
    String custom="yes";
    String exchange ="no";

    @Test
    void findByCriteria() {
        List<Announcement> foundAnnouncement = searchDao.findByCriteria(nameCar, modelCar,price,typeEngine,
                yearOfIssue ,milage,volumeEngine,  transmission, location,custom ,exchange);
        assertNotNull(foundAnnouncement);
        assertEquals(1, foundAnnouncement.size());

    }
}