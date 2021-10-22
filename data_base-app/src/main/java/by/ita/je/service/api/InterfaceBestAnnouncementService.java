package by.ita.je.service.api;

import by.ita.je.module.BestAnnouncement;

public interface InterfaceBestAnnouncementService {
    BestAnnouncement create(BestAnnouncement bestAnnouncement);
    BestAnnouncement update(long id, BestAnnouncement bestAnnouncement);
}
