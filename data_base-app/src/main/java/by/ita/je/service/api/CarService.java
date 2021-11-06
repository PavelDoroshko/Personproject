package by.ita.je.service.api;

import by.ita.je.dao.CarDao;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.entity.Car;
import by.ita.je.service.InterfaceCarService;
import org.springframework.stereotype.Service;

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
    public Car create(Car car) throws IncorrectDataException {
        if ((car.getNameCar() == "") || (car.getModelCar() == ""))
            throw new IncorrectDataException("Car");
        if ((car.getVolumeEngine() < 0) || (car.getMilage() < 0))
            throw new IncorrectDataException("Car");
        return carDao.save(car);
    }


    @Override
    @Transactional
    public Car update(Long id, Car car) {
         Car secondCar = carDao.findById(id).orElseThrow(() -> new NoFoundEntityException("Car"));
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

}
