package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.exception.NatFoundException;
import by.ita.je.module.*;
import by.ita.je.service.api.InterfaceBuisness;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
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
        List<Announcement> announcementList =new ArrayList<>();
        Announcement announcement = Announcement.builder()
                .get_up(0)
                .numberPhone(0)
                .build();
        announcement.setCar(carService.create(createCar()));
        announcement.setUser(user);
        createComent(announcement);
        announcementList.add(announcement);
        Announcement announcementNew = announcementService.create(announcement);
        user.setAnnouncementList(announcementList);
        return announcementNew;
    }

    private void createComent(Announcement announcement) {

        Coment coment = Coment.builder().message("---").build();
           announcement.setComent(coment);
        }


    @Transactional
    private Car createCar() {
        Car car = Car.builder()
                .nameCar("")
                .modelCar("")
                .custom("")
                .transmission("")
                .typeEngine("")
                .volumeEngine(0)
                .location("")
                .price(0)
                .yearOfIssue(0)
                .exchange("")
                .build();

        return car;
    }

    @Override
    @Transactional
    public Announcement update(Long id, Announcement announcement) {
       Announcement announcement1 = announcementService.readOne(id);
        Announcement announcementUpdate = announcementService.update(id, announcement);
       Car carUpdate = carService.update(announcement1.getCar().getId(), announcement.getCar());
        announcementUpdate.setCar(carUpdate );
        announcementService.update(id,  announcementUpdate);
        return announcementUpdate;
    }

    @Override
    public void deleteById(long id) {

        announcementService.deleteById(id);

    }


    @Override
    @Transactional
    public List<Announcement> readAll(User user) {
        List<Announcement> announcementListUser = new ArrayList<>();
        List<Announcement> announcementList = announcementService.readAll();
        for (Announcement announcement : announcementList) {
            if (announcement.getUser().getUser_id() == user.getUser_id()) {
                announcementListUser.add(announcement);
            }
        }
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
    public Announcement updateComent(Announcement announcement) {
        Announcement announcement1 = announcementService.readOne(announcement.getId());
        Coment coment = announcement.getComent();
 Coment comentUpdate =comentService.update(announcement1.getComent().getId(),coment);
announcement1.setComent(comentUpdate);
        return announcementService.update(announcement1.getId(),announcement1) ;
    }

    @Override
    @Transactional
 public  BestAnnouncement addAnnouncementInBestAnnouncement(Long id,User user) throws NotFoundException {
        List<BestAnnouncement> bestAnnouncementList =new ArrayList<>();
        Announcement findAnnoucment = announcementService.readOne(id);
        BestAnnouncement bestAnnouncemet =BestAnnouncement.builder()
                .announcement(findAnnoucment)
                .build();
        bestAnnouncementList.add(bestAnnouncemet);
        user.setBestAnnouncements(bestAnnouncementList);
             //   userService.update(user.getUser_id(),user);
        return bestAnnouncementService.create(bestAnnouncemet);
 }
    @Override
    @Transactional
    public CreditCart createCreditCart (User user) throws NotFoundException {
        User userNew =userDao.findById(user.getUser_id()).orElseThrow(() -> new NatFoundException("User"));
        int cashNew = (int)(Math.random()*1000);
        CreditCart creditCart = CreditCart.builder()
                .cash(cashNew)
                .build();
        creditCartService.create(creditCart);
        userNew.setCreditCart(creditCart);
return creditCart;
    }
    @Override
  public User  addBalance (User user ) throws NotFoundException {
        User userNew =userService.readOne(user.getUser_id());
        CreditCart creditCart = userNew.getCreditCart();
      int cash =  creditCart.getCash();
        int balance =userNew.getBalance();
        if(cash > balance){
            balance = balance +20;
            cash = cash -20;
        }
        else{
            throw new NotFoundException("CashAdd");
        }
        creditCart.setCash(cash);
        creditCartService.create(creditCart);
      userNew.setBalance(balance);

        return    userService.create(userNew);
    }
    public Announcement getUpAnnoncement (Announcement announcement){
        Announcement announcementFind = announcementService.readOne(announcement.getId());
        User user = announcementFind.getUser();
        List<Announcement> announcementList = user.getAnnouncementList();
   int balance =user.getBalance()-announcement.getGet_up();
   int get_up = announcementFind.getGet_up()+announcement.getGet_up();
   announcementFind.setGet_up(get_up);
   user.setBalance(balance);
   announcementService.create( announcementFind);

        return announcementFind;
    }
}



