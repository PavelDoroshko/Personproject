package by.ita.je.service.api;

import javassist.NotFoundException;
import by.ita.je.module.Car;

import java.util.List;

public interface InterfaceCarService {
    Car create(Car car);
    Car update(Long id, Car car) throws NotFoundException;
    List<Car> readAll();
    Car readOne(Long id) throws NotFoundException;
    void deleteById(Long id);
}
