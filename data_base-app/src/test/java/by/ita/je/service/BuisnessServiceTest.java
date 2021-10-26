package by.ita.je.service;

import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.*;
import by.ita.je.service.api.InterfaceAnnouncement;
import by.ita.je.service.api.InterfaceBuisness;
import by.ita.je.service.api.InterfaseUserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

        Announcement expected = getAnnouncement();
        Announcement actual = interfaceBuisness.createAnnouncement(expected);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getNumberPhone(), actual.getNumberPhone());
        Assertions.assertEquals(expected.getGet_up(), actual.getGet_up());
        Assertions.assertTrue(actual.getId() > 0);
        Assertions.assertTrue(actual.getCar().getId() > 0 && actual.getCar().getNameCar() == "BMW");
    }

    @Test
    void whenCreateAnnouncement_throwException() {
        Announcement announcement = Announcement.builder()
                .numberPhone(0)
                .car(createCar())
                .coment(createComentNew())
                .build();
        IncorrectDataException incorrectDataException = Assertions.assertThrows(IncorrectDataException.class,
                () -> interfaceBuisness.createAnnouncement(announcement));
        Assertions.assertEquals(incorrectDataException.getMessage(),
                "Введены некорректные данные для Announcement");
    }

    @SneakyThrows
    @Test
    void updateAnnouncement_thenOk() {
        Announcement announcement1 = Announcement.builder()
                .get_up(0)
                .numberPhone(2222)
                .car(createCar())
                .coment(createComentNew())
                .build();
        Announcement actual = interfaceBuisness.update(1L, announcement1);
        Assertions.assertEquals(2222, actual.getNumberPhone());
        Assertions.assertEquals(0, actual.getGet_up());

    }

    @Test
    void updateAnnouncement_thenException() {
        Announcement announcement = getAnnouncement();
        NoFoundEntityException runtimeException = Assertions.assertThrows(NoFoundEntityException.class, () -> interfaceBuisness.update(11L, announcement));
        Assertions.assertEquals(runtimeException.getMessage(),
                "Такой записи для Announcement в базе данных не существует");

    }

    @SneakyThrows
    @Test
    void deleteById() {
        interfaceBuisness.deleteById(1L);
        NoFoundEntityException runtimeException = Assertions.assertThrows(NoFoundEntityException.class, () -> interfaceAnnouncement.readOne(1L));
        Assertions.assertEquals(runtimeException.getMessage(),
                "Такой записи для Announcement в базе данных не существует");
    }


    @Test
    void readAll() {

    }

    @SneakyThrows
    @Test
    void createComent() {
        Announcement announcement1 = Announcement.builder()
                .numberPhone(1111)
                .get_up(0)
                .car(createCar())
                .build();
        Announcement announcement = interfaceBuisness.createAnnouncement(announcement1);
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
        Announcement announcement = interfaceBuisness.createAnnouncement(announcement1);
        User user = createUserNew();
        BestAnnouncement expected = BestAnnouncement.builder()
                .announcement(announcement)
                .user(user)
                .build();
        BestAnnouncement actual = interfaceBuisness.addAnnouncementInBestAnnouncement(announcement.getId(), user);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getAnnouncement().getId(), actual.getAnnouncement().getId());
    }

    @SneakyThrows
    @Test
    void createCreditCart() {
        User user = createUserNew();
        CreditCart expected = CreditCart.builder()
                .cash(500)
                .build();
        CreditCart actual = interfaceBuisness.createCreditCart(user);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getCash(), actual.getCash());
    }

    @Test
    void addBalance() {

    }

    @Test
    void getUpAnnoncement() {
    }

    private User createUserNew() {
        User user = User.builder()
                .pasword(11)
                .balance(0)
                .name("www")
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
                .get_up(0)
                .car(createCar())
                .coment(createComentNew())
                .build();
    }

}