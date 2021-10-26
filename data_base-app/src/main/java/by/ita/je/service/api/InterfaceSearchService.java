package by.ita.je.service.api;

import by.ita.je.module.Announcement;

import java.util.List;

public interface InterfaceSearchService {
    public List<Announcement> findCriteria(String nameCar, String modelCar,int price,
                                           String typeEngine,int yearOfIssue,int milage,
                                           int volumeEngine,String transmission,String location,
                                           String custom,String exchange);
}
