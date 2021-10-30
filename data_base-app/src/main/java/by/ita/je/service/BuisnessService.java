package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.dto.AnnouncementDto;
import by.ita.je.module.*;
import by.ita.je.service.api.InterfaceBuisness;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuisnessService implements InterfaceBuisness {

    private final CarService carService;
    private final ComentService comentService;
    private final BestAnnouncementService bestAnnouncementService;
    private final UserService userService;
    private final AnnouncementService announcementService;
    private final CreditCartService creditCartService;
    private final UserDao userDao;



    @Override
    @Transactional
    public Announcement createAnnouncement(Long id,Announcement announcement) throws NotFoundException {
        User user = userService.readOne(id);
        List<Announcement> announcementList =user.getAnnouncementList();
        Announcement announcement1 = announcementService.create(announcement);
        Car car = carService.create(announcement.getCar());
        Coment coment = comentService.create(announcement.getComent());
        announcement1.setComent(coment);
        announcement1.setCar(car);
        announcementList.add(announcement1);
        user.setAnnouncementList(announcementList);
       userService.create(user);
        return announcement1;
    }

    @Override
    @Transactional
    public Announcement update(Long id, Announcement announcement) {
        Announcement announcementUpdate = announcementService.update(id, announcement);
        Car carUpdate = carService.update(announcementUpdate.getCar().getId(), announcement.getCar());
        Coment coment = comentService.update(announcementUpdate.getComent().getId(), announcement.getComent());
        announcementUpdate.setCar(carUpdate);
        announcementUpdate.setComent(coment);
        return announcementUpdate;
    }

    @Override
    public void deleteById(long id) {

        announcementService.deleteById(id);

    }


    @Override
    @Transactional
    public List<Announcement> readAllAnnoucement (Long id) throws NotFoundException {

        User userFind = userService.readOne(id);

        List<Announcement> announcementListUser = userFind.getAnnouncementList();

        announcementListUser.sort(new Comparator<Announcement>() {
            @Override
            public int compare(Announcement o1, Announcement o2) {
                if (o1.getGet_up() == o2.getGet_up()) return 0;
                else if (o1.getGet_up() > o2.getGet_up()) return -1;
                else return 1;
            }
        });
        return announcementListUser;

    }


    @Override
    @Transactional
    public Coment createComent(Long id, Coment coment) {
        Announcement announcementFind = announcementService.readOne(id);
        Coment comentFind =announcementFind.getComent();
        Coment comentCreate = comentService.update(comentFind.getId(),coment);
        announcementFind.setComent(comentCreate);
        return comentCreate;
    }

    @Override
    @Transactional
    public BestAnnouncement addAnnouncementInBestAnnouncement(Long id, Long userId) throws NotFoundException {
        User user = userService.readOne(userId);
        List<BestAnnouncement> bestAnnouncementList = user.getBestAnnouncements();
        Announcement findAnnoucment = announcementService.readOne(id);
        BestAnnouncement bestAnnouncemet = BestAnnouncement.builder()
                .announcement(findAnnoucment)
                .build();
        bestAnnouncementService.create(bestAnnouncemet);
        bestAnnouncementList.add(bestAnnouncemet);
        user.setBestAnnouncements(bestAnnouncementList);
        userService.create(user);
        return bestAnnouncemet;
    }


    @Override
    @Transactional
    public CreditCart createCreditCart(Long id) throws NotFoundException {
        User userNew = userService.readOne(id);
        CreditCart creditCartOld = new CreditCart();
        if(userNew.getCreditCart()==null){
        int cashNew = 500;
        CreditCart creditCartNew = CreditCart.builder()
                .cash(cashNew)
                .build();
        creditCartService.create(creditCartNew);
        userNew.setCreditCart(creditCartNew);
            return creditCartNew;
        }
        else{ creditCartOld =userNew.getCreditCart();}
        return creditCartOld;
    }


    @Override
    @Transactional
    public User addBalance(Long userId) throws NotFoundException {
        User userNew = userService.readOne(userId);
        CreditCart creditCart = userNew.getCreditCart();
        int cash = creditCart.getCash();
        int balance = userNew.getBalance();
            balance = balance + 20;
            cash = cash - 20;
        creditCart.setCash(cash);
        userNew.setBalance(balance);
creditCartService.create(creditCart);
        return userNew;
    }


    @Override
    @Transactional
    public Announcement getUpAnnoncement(long userId, AnnouncementDto announcement) {
        int money = 3;
        Announcement announcementFind = announcementService.readOne(announcement.getId());
        User user = announcementFind.getUser();
        List<Announcement> announcementList = user.getAnnouncementList();
        int balance = user.getBalance() - money;
        int get_up = announcementFind.getGet_up() + money;
        announcementFind.setGet_up(get_up);
        user.setBalance(balance);
userService.create(user);
        return announcementService.update(announcement.getId(), announcementFind);
    }

    @SneakyThrows
    @Override
    @Transactional
    public Announcement getUpAnnoncementMoney(Long id,int money,Long userId) {
        Announcement announcementFind = announcementService.readOne(id);
        User user = userService.readOne(userId);
        int balance = user.getBalance() - money;
        int get_up = announcementFind.getGet_up() + money;
        announcementFind.setGet_up(get_up);
        user.setBalance(balance);
        return announcementService.update(id, announcementFind);
    }



}



