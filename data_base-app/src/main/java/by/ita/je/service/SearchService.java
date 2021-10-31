package by.ita.je.service;

import by.ita.je.dao.SearchDao;
import by.ita.je.module.Announcement;
import by.ita.je.service.api.InterfaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor

public class SearchService implements InterfaceSearchService {
    private final SearchDao searchDao;

    @Override
    public List<Announcement> findCriteria(String nameCar, String modelCar, int price,
                                           String typeEngine, int yearOfIssue, int milage,
                                           int volumeEngine, String transmission, String location,
                                           String custom, String exchange) {
        return searchDao.findByCriteria(nameCar, modelCar, price,
                typeEngine, yearOfIssue, milage,
                volumeEngine, transmission, location,
                custom, exchange);
    }

}
