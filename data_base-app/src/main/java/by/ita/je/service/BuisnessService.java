package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.module.*;
import by.ita.je.service.api.InterfaceBuisness;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
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
    public Announcement createAnnouncement(User user) {
        List<Announcement> announcementList = new ArrayList<>();
        Announcement announcement = Announcement.builder()
                .get_up(0)
                .numberPhone(0)
                .build();
        announcement.setUser(user);
        // createComent(announcement);
        announcementList.add(announcement);
        Announcement announcementNew = announcementService.create(announcement);
        user.setAnnouncementList(announcementList);
        return announcementNew;
    }

    @Override
    @Transactional
    public Announcement createAnnouncement(Announcement announcement) throws NotFoundException {
        Car car = carService.create(announcement.getCar());
        Coment coment = comentService.create(announcement.getComent());
        announcement.setComent(coment);
        announcement.setCar(car);
        announcement.setUser(announcement.getUser());
        return announcementService.create(announcement);
    }

    @Override
    @Transactional
    public Announcement update(Long id, Announcement announcement) {
        Announcement announcementFind = announcementService.readOne(id);
        Announcement announcementUpdate = announcementService.update(id, announcement);
        Car carUpdate = carService.update(announcementUpdate.getCar().getId(), announcement.getCar());
        Coment coment = comentService.update(announcementUpdate.getComent().getId(), announcement.getComent());
        announcementUpdate.setCar(carUpdate);
        announcementUpdate.setComent(coment);
        //Announcement announcementUpdate = announcementService.update(id, announcementFind);
        return announcementUpdate;
    }

    @Override
    public void deleteById(long id) {

        announcementService.deleteById(id);

    }


    @Override
    @Transactional
    public List<Announcement> readAll(User user) throws NotFoundException {
        User userFind = userService.readOne(user.getUser_id());

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
    public BestAnnouncement addAnnouncementInBestAnnouncement(Long id, User user) throws NotFoundException {
        List<BestAnnouncement> bestAnnouncementList = new ArrayList<>();
        Announcement findAnnoucment = announcementService.readOne(id);
        BestAnnouncement bestAnnouncemet = BestAnnouncement.builder()
                .announcement(findAnnoucment)
                .build();
        bestAnnouncementList.add(bestAnnouncemet);
        user.setBestAnnouncements(bestAnnouncementList);
        return bestAnnouncementService.create(bestAnnouncemet);
    }


    @Override
    @Transactional
    public CreditCart createCreditCart(User user) throws NotFoundException {
        User userNew = userService.readOne(user.getUser_id());
        //int cashNew = (int) (Math.random() * 1000);
        int cashNew = 500;
        CreditCart creditCart = CreditCart.builder()
                .cash(cashNew)
                .build();
        creditCartService.create(creditCart);
        userNew.setCreditCart(creditCart);
       // userService.create(userNew);
        return creditCart;
    }


    @Override
    @Transactional
    public User addBalance(User user) throws NotFoundException {
        User userNew = userService.readOne(user.getUser_id());
        CreditCart creditCart = userNew.getCreditCart();
        int cash = creditCart.getCash();
        int balance = userNew.getBalance();
        //if (cash > balance) {
            balance = balance + 20;
            cash = cash - 20;
      //  } else {
           // throw new NotFoundException("CashAdd");
     //   }
        creditCart.setCash(cash);
        userNew.setBalance(balance);

        return userNew;
    }


    @Override
    @Transactional
    public Announcement getUpAnnoncement(Announcement announcement) {
        Announcement announcementFind = announcementService.readOne(announcement.getId());
        User user = announcementFind.getUser();
        List<Announcement> announcementList = user.getAnnouncementList();
        int balance = user.getBalance() - announcement.getGet_up();
        int get_up = announcementFind.getGet_up() + announcement.getGet_up();
        announcementFind.setGet_up(get_up);
        user.setBalance(balance);
        //announcementService.update(announcement.getId(),announcementFind);

        return announcementService.update(announcement.getId(), announcementFind);
    }
}



