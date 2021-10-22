package by.ita.je.service;

import by.ita.je.dao.CarDao;
import by.ita.je.exception.NatFoundException;
import javassist.NotFoundException;
import by.ita.je.module.Car;
import org.springframework.stereotype.Service;
import by.ita.je.service.api.InterfaceCarService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CarService implements InterfaceCarService {
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    private final CarDao carDao;

    @Override
    @Transactional
    public Car create(Car car) {
        return carDao.save(car);
    }


    @Override
    @Transactional
    public Car update(Long id, Car car) {
        Car secondCar = carDao.findById(id).orElseThrow(() -> new NatFoundException("Car"));
        secondCar.setNameCar(car.getNameCar());
        secondCar.setModelCar(car.getModelCar());
        secondCar.setCustom(car.getCustom());
        secondCar.setExchange(car.getExchange());
        secondCar.setLocation(car.getLocation());
        secondCar.setMilage(car.getMilage());
        secondCar.setTransmission(car.getTransmission());
        secondCar.setTypeEngine(car.getTypeEngine());
        secondCar.setYearOfIssue(car.getYearOfIssue());
        return carDao.save(secondCar);
    }

    @Override
    public List<Car> readAll() {
        final Spliterator<Car> result = carDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }

    @Override
    public Car readOne(Long id) throws NotFoundException {
        return carDao.findById(id).orElseThrow(() -> new NatFoundException("Car"));
    }

    @Override
    public void deleteById(Long id) {
        carDao.deleteById(id);
    }


}
