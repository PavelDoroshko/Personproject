package by.ita.je.dao;

import by.ita.je.entity.Announcement;

import java.util.List;

public interface SearchDao {

    List<Announcement> findByCriteria(String nameCar, String modelCar,int price,
                                      String typeEngine,int yearOfIssue,int milage,
                                      int volumeEngine,String transmission,String location,
                                      String custom,String exchange);
}
