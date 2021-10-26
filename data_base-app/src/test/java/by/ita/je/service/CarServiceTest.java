package by.ita.je.service;

import by.ita.je.dao.CarDao;
import by.ita.je.dao.UserDao;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CarServiceTest {
    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarService carService;

private static Car carGiven = Car.builder()
        .id(1L)
        .nameCar("Audi")
        .modelCar("100")
        .custom("yes")
        .exchange("no")
        .location("Brest")
        .milage(700000)
        .typeEngine("diesel")
        .yearOfIssue(20)
        .transmission("mehanic")
        .volumeEngine(2)
        .build();
    private static Car carGet = Car.builder()
            .nameCar("fiat")
            .build();
  List<Car> cars = new ArrayList<>();

    {
        cars.add(carGiven);
        cars.add(carGet );
    }
    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void whenCreate_returnCar() {
        Mockito.when(carDao.save(carGiven)).thenReturn(carGiven);
        Car actual = carService.create(carGiven);
        Car expected = carGiven;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(carDao, Mockito.times(1)).save(carGiven);
    }
    @Test
    void whenCreate_returnException() {
        Car car  = Car.builder()
                .nameCar("")
                .build();
        IncorrectDataException incorrectDataException = Assertions
                .assertThrows(IncorrectDataException.class,()->carService.create(car));
        Assertions.assertEquals(incorrectDataException.getMessage(),
                "Введены некорректные данные для Car");
        Mockito.verify(carDao, Mockito.times(0)).save(Mockito.any());


    }

    @Test
    void whenUpdate_returnCar() {
        Mockito.when(carDao.findById(1L)).thenReturn(Optional.ofNullable(carGiven));
        Mockito.when(carDao.save(carGiven)).thenReturn(carGet);
        Car actual = carService.update(1L, carGiven);
        Car expected = carGet;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(carDao, Mockito.times(1)).findById(1L);
        Mockito.verify(carDao, Mockito.times(1)).save(carGiven);


    }
    @Test
    void whenUpdate_throwException() {
        Car car  = Car.builder()
                .nameCar("bmw")
                .build();
        Mockito.when(carDao.findById(4L)).thenReturn(Optional.empty());
        NoFoundEntityException noEntityException = Assertions.assertThrows( NoFoundEntityException.class,
                ()->carService.update(4L, car));
        Assertions.assertEquals(noEntityException.getMessage(),
                "Такой записи для Car в базе данных не существует");
        Mockito.verify(carDao,Mockito.times(1)).findById(4L);
        Mockito.verify(carDao, Mockito.times(0)).save(Mockito.any());

    }
    @Test
    void whenReadAll_returnCars() {
        Mockito.when(carDao.findAll()).thenReturn(cars);
        List<Car> actual = carService.readAll();
        List<Car> expected = cars;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(carDao, Mockito.times(1)).findAll();
    }




}