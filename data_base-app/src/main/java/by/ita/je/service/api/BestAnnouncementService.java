package by.ita.je.service.api;

import by.ita.je.dao.BestAnnouncementDao;
import by.ita.je.entity.BestAnnouncement;
import by.ita.je.service.InterfaceBestAnnouncementService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public List<BestAnnouncement> readAll() {
        final Spliterator<BestAnnouncement> result = bestAnnouncementDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }
}





