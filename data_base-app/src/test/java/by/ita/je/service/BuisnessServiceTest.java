package by.ita.je.service;

import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.*;
import by.ita.je.service.api.InterfaceAnnouncement;
import by.ita.je.service.api.InterfaceBuisness;
import by.ita.je.service.api.InterfaseUserService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BuisnessServiceTest {

    @Autowired
    InterfaceBuisness interfaceBuisness;
    @Autowired
    InterfaceAnnouncement interfaceAnnouncement;
    @Autowired
    InterfaseUserService interfaseUserService;
    @SneakyThrows
    @Test
    void createAnnouncement_thenOk() {
         User user = createUserNew();
        interfaseUserService.create(user);
        Announcement expected = getAnnouncement();
        Announcement actual = interfaceBuisness.createAnnouncement(user.getUser_id(),expected);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getNumberPhone(), actual.getNumberPhone());
        Assertions.assertEquals(expected.getGet_up(), actual.getGet_up());
        Assertions.assertTrue(actual.getId() > 0);
        Assertions.assertTrue(actual.getCar().getId() > 0 && actual.getCar().getNameCar() == "BMW");

    }

    @Test
    void whenCreateAnnouncement_throwException() {
        User user = createUserNew();
        interfaseUserService.create(user);
        Announcement announcement = Announcement.builder()
                .numberPhone(0)
                .car(createCar())
                .coment(createComentNew())
                .get_up(0)
                .build();
        IncorrectDataException incorrectDataException = Assertions.assertThrows(IncorrectDataException.class,
                () -> interfaceBuisness.createAnnouncement(user.getUser_id(), announcement));
        Assertions.assertEquals(incorrectDataException.getMessage(),
                "Введены некорректные данные для Announcement");
    }

    @SneakyThrows
    @Test
    void updateAnnouncement_thenOk() {
        Announcement announcementFirst = Announcement.builder()
                .get_up(0)
                .numberPhone(2222)
                .car(createCar())
                .coment(createComentNew())
                .build();
        interfaceAnnouncement.create(announcementFirst);
        Announcement announcementSecond = getAnnouncement();
        Announcement actual = interfaceBuisness.update(2L, announcementSecond);
        Assertions.assertEquals(1111, actual.getNumberPhone());
        Assertions.assertEquals(2, actual.getGet_up());

    }

    @Test
    void updateAnnouncement_thenException() {
        Announcement announcement = getAnnouncement();
        NoFoundEntityException runtimeException = Assertions.assertThrows(NoFoundEntityException.class, () -> interfaceBuisness.update(14L, announcement));
        Assertions.assertEquals(runtimeException.getMessage(),
                "Такой записи для Announcement в базе данных не существует");

    }

    @SneakyThrows
    @Test
    void deleteById() {
        interfaceBuisness.deleteById(2L);
        NoFoundEntityException runtimeException = Assertions.assertThrows(NoFoundEntityException.class, () -> interfaceAnnouncement.readOne(2L));
        Assertions.assertEquals(runtimeException.getMessage(),
                "Такой записи для Announcement в базе данных не существует");
    }


    @Test
    void readAll() throws NotFoundException {
        User userExpected = createUserNew();
        interfaseUserService.create(userExpected );
        Announcement announcement2 = getAnnouncement();
       interfaceBuisness.createAnnouncement(userExpected .getUser_id(),announcement2);
        Announcement announcement3 = getAnnouncement();
        interfaceBuisness.createAnnouncement(userExpected .getUser_id(),announcement3);
        Long id = userExpected .getUser_id();
        List<Announcement> announcementListactual = interfaceBuisness.readAllAnnoucement(id);
        Assertions.assertEquals(2, announcementListactual.size());


    }

    @SneakyThrows
    @Test
    void createComent() {
        Coment comentFirst = Coment.builder().message("--").build();
        Announcement announcement1 = Announcement.builder()
                .numberPhone(1111)
                .get_up(0)
                .car(createCar())
                .coment(comentFirst)
                .build();
        Announcement announcement = interfaceAnnouncement.create(announcement1);
        Coment expected = createComentNew();
        Coment actual = interfaceBuisness.createComent(announcement.getId(), expected);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getMessage(), actual.getMessage());
    }

    @SneakyThrows
    @Test
    void addAnnouncementInBestAnnouncement() {
     Announcement announcement1 = Announcement.builder()
                .numberPhone(1111)
                .get_up(0)
                .car(createCar())
                .build();
     interfaceAnnouncement.create(announcement1);
        User user = createUserNew();
        interfaseUserService.create(user);
        BestAnnouncement expected = BestAnnouncement.builder()
               .announcement(announcement1)
                .user(user)
                .build();
        BestAnnouncement actual = interfaceBuisness.addAnnouncementInBestAnnouncement(announcement1.getId(), user.getUser_id());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getAnnouncement().getId(), actual.getAnnouncement().getId());
    }

    @SneakyThrows
    @Test
    void createCreditCart() {
        User user = createUserNew();
        interfaseUserService.create(user);
        CreditCart expected = CreditCart.builder()
                .cash(500)
                .build();
        CreditCart actual = interfaceBuisness.createCreditCart(user.getUser_id());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getCash(), actual.getCash());
    }

    @SneakyThrows
    @Test
    void addBalance() {
        User actual = createUserNew();
        CreditCart creditCart = CreditCart.builder()
                .cash(500)
                .build();
        actual.setCreditCart(creditCart);
        interfaseUserService.create(actual);
        User expect = interfaceBuisness.addBalance(actual.getUser_id());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expect.getBalance(), actual.getBalance()+20);

    }

    @Test
    void getUpAnnoncement() {
        int money =10;
        User user = User.builder()
                .pasword(12)
                .balance(23)
                .build();
        interfaseUserService.create(user);
Announcement announcement = Announcement.builder()
        .numberPhone(12)
        .car(createCar())
        .coment(createComentNew())
        .get_up(0)
        .build();
interfaceAnnouncement.create(announcement);
int get_up_actual = announcement.getGet_up();
Announcement announcementExpected = interfaceBuisness.getUpAnnoncementMoney(announcement.getId(),money,user.getUser_id());
        Assertions.assertEquals(get_up_actual+10, announcementExpected.getGet_up());
    }

    private User createUserNew() {
        User user = User.builder()
                .pasword(11)
                .balance(0)
                .login("www")
                .build();
        return interfaseUserService.create(user);

    }

    private Coment createComentNew() {
        return Coment.builder()
                .message("ssss")
                .build();
    }

    private Car createCar() {
        return Car.builder()
                .nameCar("BMW")
                .modelCar("ww")
                .build();
    }


    private Announcement getAnnouncement() {
        return Announcement.builder()
                .numberPhone(1111)
                .get_up(2)
                .car(createCar())
                .coment(createComentNew())
                .build();
    }

}