package by.ita.je.service;

import by.ita.je.dto.AnnouncementDto;
import by.ita.je.entity.*;
import javassist.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

public interface InterfaceBuisness {
    public Announcement createAnnouncement(Long id, Announcement announcement) throws NotFoundException;

    Announcement getUpAnnoncement(long userId, AnnouncementDto announcement);

    User addBalance(Long id) throws NotFoundException;

    CreditCart createCreditCart(long id) throws NotFoundException;


    Announcement update(Long id, Announcement announcement) throws NotFoundException;

    List<Announcement> readAllAnnoucement(Long id) throws NotFoundException;

    void deleteById(long id);



    @Transactional
    BestAnnouncement addAnnouncementInBestAnnouncement(Long Id, Long userId) throws NotFoundException;



    User findUserByLoginPasword(String login, int pasword);
}
