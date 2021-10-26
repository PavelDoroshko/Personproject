package by.ita.je.service.api;

import by.ita.je.module.*;
import javassist.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

public interface InterfaceBuisness {
    public Announcement createAnnouncement(Announcement announcement) throws NotFoundException;
    Announcement getUpAnnoncement (Announcement announcement);

    User  addBalance (User user ) throws NotFoundException;

    CreditCart createCreditCart (User user) throws NotFoundException;

    Announcement createAnnouncement(User user);

    Announcement update(Long id, Announcement announcement) throws NotFoundException;

    List<Announcement> readAll(User user) throws NotFoundException;

    void deleteById(long id);

    @Transactional
    Coment createComent(Long id,Coment coment);

    @Transactional
    BestAnnouncement addAnnouncementInBestAnnouncement(Long Id,User user) throws NotFoundException;
}
