package by.ita.je.service.api;

import by.ita.je.dto.AnnouncementDto;
import javassist.NotFoundException;
import by.ita.je.module.Announcement;

import java.util.List;

public interface InterfaceAnnouncement {
    Announcement create(Announcement announcement);
    Announcement update(long id, Announcement announcement) throws NotFoundException;
    List<Announcement> readAll();
    Announcement readOne(Long id) throws NotFoundException;
    void deleteById(Long id);
}
