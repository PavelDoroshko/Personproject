package by.ita.je.dao;

import by.ita.je.module.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarDao extends CrudRepository<Car,Long> {
}
