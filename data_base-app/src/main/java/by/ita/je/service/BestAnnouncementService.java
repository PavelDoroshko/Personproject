package by.ita.je.service;

import by.ita.je.dao.BestAnnouncementDao;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.BestAnnouncement;
import by.ita.je.service.api.InterfaceBestAnnouncementService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BestAnnouncementService implements InterfaceBestAnnouncementService {
    private BestAnnouncementDao bestAnnouncementDao;

    public BestAnnouncementService(BestAnnouncementDao bestAnnouncementDao) {
        this.bestAnnouncementDao = bestAnnouncementDao;
    }

    @Override
    @Transactional
    public BestAnnouncement create(BestAnnouncement bestAnnouncement) {
        BestAnnouncement createbestAnnouncement = BestAnnouncement.builder()
                .announcement(bestAnnouncement.getAnnouncement())
                .user(bestAnnouncement.getUser())
                .build();
        return bestAnnouncementDao.save(createbestAnnouncement);
    }


    }





