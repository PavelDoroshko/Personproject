package by.ita.je.service.api;

import by.ita.je.module.BestAnnouncement;

import java.util.List;

public interface InterfaceBestAnnouncementService {
    BestAnnouncement create(BestAnnouncement bestAnnouncement);
    List<BestAnnouncement> readAll();
}
