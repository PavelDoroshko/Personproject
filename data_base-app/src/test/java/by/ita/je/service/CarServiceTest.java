package by.ita.je.service;

import by.ita.je.dao.CarDao;
import by.ita.je.exception.NatFoundException;
import lombok.SneakyThrows;
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

import static org.mockito.Mockito.doNothing;

class CarServiceTest {
    @Mock
    private CarDao carDao;
    @InjectMocks
    private CarService carService;
private static Car carGiven = Car.builder()
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

    private static List<Car> cars = new ArrayList<>();
    {cars.add(carGiven);}

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

    @SneakyThrows
    @Test
    void whenUpdate_returnCar() {
        Mockito.when(carDao.findById(1L)).thenReturn(Optional.ofNullable(carGiven));
        Mockito.when(carDao.save(carGiven)).thenReturn(carGiven);
        Car actual =carService.update(1L, carGiven);
        Car expected = carGiven;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(carDao, Mockito.times(1)).findById(1L);
        Mockito.verify(carDao, Mockito.times(1)).save(carGiven);
    }
    @Test
    void whenUpdate_throwNatFoundException() {
        Mockito.when(carDao.findById(2L)).thenReturn(Optional.empty());

        NatFoundException natFoundException;
        natFoundException = Assertions.assertThrows(NatFoundException.class,
                () -> carService.update(2L,carGiven));
        Mockito.verify(carDao, Mockito.times(1)).findById(2L);

    }
    @Test
    void whenReadAll_returnCars() {
        Mockito.when(carDao.findAll()).thenReturn(cars);
        List<Car> actual = carService.readAll();
        List<Car> expected = cars;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(carDao, Mockito.times(1)).findAll();
    }

    @SneakyThrows
    @Test
    void whenReadOne_returnCar() {
        Mockito.when(carDao.findById(1l)).thenReturn(Optional.ofNullable(carGiven));
        Car actual = carService.readOne(1L);
        Car excepted = carGiven;
        Assertions.assertEquals(excepted, actual);
        Mockito.verify(carDao, Mockito.times(1)).findById(1L);

    }

    @Test
    void whenDeleteById_returnOk() {
        doNothing().when(carDao).deleteById(1L);
        carService.deleteById(1L);
        Mockito.verify(carDao, Mockito.times(1)).deleteById(1L);
    }
}