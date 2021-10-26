package by.ita.je.dao.impl;

import by.ita.je.dao.SearchDao;
import by.ita.je.module.Announcement;
import by.ita.je.module.Announcement_;
import by.ita.je.module.Car;
import by.ita.je.module.Car_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Announcement> findByCriteria(String nameCar, String modelCar,int price,
                                             String typeEngine,int yearOfIssue,int milage,
                                             int volumeEngine,String transmission,String location,
                                             String custom,String exchange) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteria = cb.createQuery(Announcement.class);
        Root<Announcement> announcementRoot = criteria.from(Announcement.class);
        Join<Announcement, Car> carJoin = announcementRoot.join(Announcement_.car);
        Predicate name = cb.equal(carJoin.get(Car_.nameCar), nameCar);
        Predicate model = cb.equal(carJoin.get(Car_.modelCar), modelCar);
        Predicate priceCar = cb.equal(carJoin.get(Car_.price), price);
        Predicate typeEngineCar = cb.equal(carJoin.get(Car_.typeEngine), typeEngine);
        Predicate yearOfIssueCar = cb.equal(carJoin.get(Car_.yearOfIssue), yearOfIssue);
        Predicate milageCar = cb.equal(carJoin.get(Car_.milage), milage);
        Predicate volumeEngineCar = cb.equal(carJoin.get(Car_.volumeEngine), volumeEngine);
        Predicate transmissionCar = cb.equal(carJoin.get(Car_.transmission), transmission);
        Predicate locationCar = cb.equal(carJoin.get(Car_.location), location);
        Predicate customCar = cb.equal(carJoin.get(Car_.custom), custom);
        Predicate exchangeCar = cb.equal(carJoin.get(Car_.exchange), exchange);
        criteria.select(announcementRoot).where(cb.or(name, model, priceCar, typeEngineCar, yearOfIssueCar,
                milageCar, volumeEngineCar, transmissionCar, locationCar, customCar, exchangeCar));
        TypedQuery<Announcement> query = entityManager.createQuery(criteria);
        List<Announcement> announcementList = query.getResultList();

        List<Announcement> announcementList1 = new ArrayList<>();
        List<Announcement> announcementList2 = new ArrayList<>();
        List<Announcement> announcementList3 = new ArrayList<>();

        for (Announcement announcement : announcementList) {
            if (announcement.getCar().getNameCar().equals(nameCar)) {
                announcementList1.add(announcement);
            }
        }
        for (Announcement announcement : announcementList1) {
            if (announcement.getCar().getModelCar().equals(modelCar)) {
                announcementList2.add(announcement);
                return announcementList2;
            } else {
                return announcementList1;
            }
        }

      return announcementList;
    }
}