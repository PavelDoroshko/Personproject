package by.ita.je.service.api;


import by.ita.je.dao.AnnouncementDao;
import by.ita.je.dao.UserDao;
import by.ita.je.entity.Announcement;
import by.ita.je.entity.User;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.service.InterfaceAnnouncement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementService implements InterfaceAnnouncement {
    private final AnnouncementDao announcementDao;
    private final UserDao userDao;


    @Override
    @Transactional
    public Announcement create(Announcement announcement) {
        if ((announcement.getNumberPhone() == 0))
            throw new IncorrectDataException("Announcement");
        return announcementDao.save(announcement);
    }

    @Override
    @Transactional
    public Announcement update(long id, Announcement announcement) throws NoFoundEntityException {
        Announcement secondannouncement = announcementDao.findById(id).orElseThrow(() -> new NoFoundEntityException("Announcement"));
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
    public Announcement readOneWithUser(Long id, Long user_id) throws NoFoundEntityException {
        Announcement announcementFind = Announcement.builder().build();
    User user = userDao.findById(user_id).orElseThrow(() -> new NoFoundEntityException("User"));
    List<Announcement> announcementList = user.getAnnouncementList();
        Announcement announcementFind1 = new Announcement();
    for(Announcement announcement:announcementList){
        if(announcement.getId()==id){
            announcementFind1=announcement;
            return announcementFind1;
        }
    }
        return  announcementFind ;

    }
    @Override
    @Transactional
    public Announcement readOne(Long id) throws NoFoundEntityException {

        return announcementDao.findById(id).orElseThrow(() -> new NoFoundEntityException("Announcement"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        announcementDao.deleteById(id);
    }
}
