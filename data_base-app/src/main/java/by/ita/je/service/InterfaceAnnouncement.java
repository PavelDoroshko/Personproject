package by.ita.je.service;

import javassist.NotFoundException;
import by.ita.je.entity.Announcement;

import java.util.List;

public interface InterfaceAnnouncement {
    Announcement create(Announcement announcement);
    Announcement update(long id, Announcement announcement) throws NotFoundException;
    List<Announcement> readAll();
    Announcement readOneWithUser(Long id, Long user_id) throws NotFoundException;
    void deleteById(Long id);
    Announcement readOne(Long id);
}
