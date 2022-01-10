package by.ita.je.dao;

import by.ita.je.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarDao extends CrudRepository<Car,Long> {
}
