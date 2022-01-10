package by.ita.je.service;

import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.entity.*;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    @Autowired
    InterfaceCarService interfaseCarService;
    @SneakyThrows
    @Test
    void createAnnouncement_thenOk() {
         User user = createUserNew();
        interfaseUserService.create(user);
        Announcement expected = getAnnouncement();
        Announcement actual = interfaceBuisness.createAnnouncement(user.getId(),expected);
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
                () -> interfaceBuisness.createAnnouncement(user.getId(), announcement));
        Assertions.assertEquals(incorrectDataException.getMessage(),
                "Введены некорректные данные для Announcement");
    }

    @SneakyThrows
    @Test
    void updateAnnouncement_thenOk() {
        Car car1 = Car.builder()
                .nameCar("www")
                .modelCar("www")
                .build();
         Coment coment1 = Coment.builder()
                 .message("ok")
                 .build();
        Announcement announcementFirst = Announcement.builder()
                .get_up(0)
                .numberPhone(2222)
                .car(car1)
                .coment(coment1)
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
       interfaceBuisness.createAnnouncement(userExpected.getId(),announcement2);
        Announcement announcement3 = getAnnouncement();
        interfaceBuisness.createAnnouncement(userExpected.getId(),announcement3);
        Long id = userExpected.getId();
        List<Announcement> announcementListactual = interfaceBuisness.readAllAnnoucement(id);
        Assertions.assertEquals(2, announcementListactual.size());


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
        BestAnnouncement actual = interfaceBuisness.addAnnouncementInBestAnnouncement(announcement1.getId(), user.getId());
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
        CreditCart actual = interfaceBuisness.createCreditCart(user.getId());
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
        User expect = interfaceBuisness.addBalance(actual.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expect.getBalance(), actual.getBalance()+20);

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