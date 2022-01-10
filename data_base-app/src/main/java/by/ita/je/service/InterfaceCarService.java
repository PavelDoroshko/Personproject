package by.ita.je.service;

import javassist.NotFoundException;
import by.ita.je.entity.Car;

import java.util.List;

public interface InterfaceCarService {
    Car create(Car car);
    Car update(Long id, Car car) throws NotFoundException;


}
