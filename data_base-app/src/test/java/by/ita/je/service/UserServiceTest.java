package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;


class UserServiceTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;

    private static User userGiven = User.builder()
            .login("pavel")
            .balance(0)
            .pasword(123)
            .build();

    private static List<User> cars = new ArrayList<>();
    {cars.add(userGiven);}

    @BeforeEach
   public void openMocks() {
       MockitoAnnotations.openMocks(this); }
    @Test
    void createUser_thenOk() {
        Mockito.when(userDao.save(userGiven)).thenReturn(userGiven);
        User actual = userService.create(userGiven);
       User expected = userGiven;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userDao, Mockito.times(1)).save(userGiven);
    }
    @Test
    void createUser_throwIncorrectDataException() {
        User user =User.builder().build();
        IncorrectDataException incorrectDataException = Assertions
                .assertThrows(IncorrectDataException.class,()->userService.create(user));
        Assertions.assertEquals(incorrectDataException.getMessage(),
                "Введены некорректные данные для User");
        Mockito.verify(userDao, Mockito.times(0)).save(Mockito.any());


    }

    @SneakyThrows
    @Test
    void readOneUser_thenOk() {
        Mockito.when(userDao.findById(1L)).thenReturn(Optional.ofNullable(userGiven));
        User expected = userService.readOne(1L);
        User actual =userGiven;
        Assertions.assertEquals(expected,actual);
        Mockito.verify(userDao, Mockito.times(1)).findById(1L);

    }
    @Test
    void readOneUser_thenException() {
        User user =User.builder().build();
        Mockito.when(userDao.findById(11L)).thenReturn(Optional.empty());
        NoFoundEntityException runtimeException = Assertions.assertThrows(NoFoundEntityException.class,()->userService.readOne(11L));
        Assertions.assertEquals(runtimeException.getMessage(),
                "Такой записи для User в базе данных не существует");
        Mockito.verify(userDao, Mockito.times(1)).findById(11L);

    }
    @SneakyThrows
    @Test
    void deleteById_thenOk() {
        userService.deleteById(1L);
        Mockito.verify(userDao, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deleteById_thenThrowException() {
        doThrow(new NoFoundEntityException("User")).when(userDao).deleteById(4L);
        NoFoundEntityException noFoundEntityException = Assertions.assertThrows(NoFoundEntityException.class,
                ()->userService.deleteById(4L));
        Assertions.assertEquals(noFoundEntityException.getMessage(),
                "Такой записи для User в базе данных не существует");

    }
}