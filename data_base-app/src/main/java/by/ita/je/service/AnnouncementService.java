package by.ita.je.service;

import by.ita.je.dao.AnnouncementDao;
import by.ita.je.exception.NatFoundException;
import by.ita.je.module.Announcement;
import org.springframework.stereotype.Service;
import by.ita.je.service.api.InterfaceAnnouncement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class AnnouncementService implements InterfaceAnnouncement {
    private final AnnouncementDao announcementDao;

    public AnnouncementService(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    @Override
    @Transactional
    public Announcement create(Announcement announcement) {
        return announcementDao.save(announcement);
    }

    @Override
    public Announcement update(long id, Announcement announcement) throws NatFoundException {
        Announcement secondannouncement = announcementDao.findById(id).orElseThrow(() -> new NatFoundException("announcement"));
        secondannouncement.setGet_up(announcement.getGet_up());
        secondannouncement.setNumberPhone(announcement.getNumberPhone());
        return announcementDao.save(secondannouncement);
    }

    @Override
    public List<Announcement> readAll() {
        final Spliterator<Announcement> result = announcementDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Announcement readOne(Long id) throws NatFoundException {
        return announcementDao.findById(id).orElseThrow(() -> new NatFoundException("announcement"));
    }

    @Override
    public void deleteById(Long id) {
        announcementDao.deleteById(id);
    }
}
