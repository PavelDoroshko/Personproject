package by.ita.je.service.api;

import by.ita.je.module.*;
import javassist.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

public interface InterfaceBuisness {
    public Announcement createAnnouncement(Long id,Announcement announcement) throws NotFoundException;
    Announcement getUpAnnoncement (Announcement announcement);

    User  addBalance (Long id) throws NotFoundException;

    CreditCart createCreditCart (Long id) throws NotFoundException;



    Announcement update(Long id, Announcement announcement) throws NotFoundException;

    List<Announcement> readAllAnnoucement (Long id) throws NotFoundException;

    void deleteById(long id);

    @Transactional
    Coment createComent(Long id,Coment coment);

    @Transactional
    BestAnnouncement addAnnouncementInBestAnnouncement(Long Id,Long userId) throws NotFoundException;


    @Transactional
    public Announcement getUpAnnoncementMoney(Long id,int money,Long userId);


}
