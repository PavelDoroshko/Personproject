package by.ita.je.service;

import by.ita.je.dao.BestAnnouncementDao;
import by.ita.je.exception.NatFoundException;
import by.ita.je.module.BestAnnouncement;
import by.ita.je.service.api.InterfaceBestAnnouncementService;
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
        return bestAnnouncementDao.save(bestAnnouncement);
    }

    @Override
    public BestAnnouncement update(long id, BestAnnouncement bestAnnouncement) {
        return null;
    }


  /*  @Override
    public BestAnnouncement update(long id, BestAnnouncement bestAnnouncement){
        BestAnnouncement secondBestAnnouncement = bestAnnouncementDao.findById(id).orElseThrow(()-> new NatFoundException("Phone"));
secondBestAnnouncement.setNumberPhone(bestAnnouncement.getNumberPhone());
return bestAnnouncementDao.save(secondBestAnnouncement);
    }*/
}

