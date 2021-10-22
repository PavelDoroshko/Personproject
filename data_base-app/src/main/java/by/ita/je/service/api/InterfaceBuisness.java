package by.ita.je.service.api;

import by.ita.je.module.*;
import javassist.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

public interface InterfaceBuisness {
    Announcement getUpAnnoncement (Announcement announcement);

    User  addBalance (User user ) throws NotFoundException;

    CreditCart createCreditCart (User user) throws NotFoundException;

    Announcement createAnnouncement(User user);

    Announcement update(Long id, Announcement announcement) throws NotFoundException;

    List<Announcement> readAll(User user);

    void deleteById(long id);

    @Transactional
    Announcement updateComent(Announcement announcement);

    @Transactional
    BestAnnouncement addAnnouncementInBestAnnouncement(Long Id, User user) throws NotFoundException;
}
