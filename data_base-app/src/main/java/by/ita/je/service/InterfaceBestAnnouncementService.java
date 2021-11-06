package by.ita.je.service;

import by.ita.je.entity.BestAnnouncement;

import java.util.List;

public interface InterfaceBestAnnouncementService {
    BestAnnouncement create(BestAnnouncement bestAnnouncement);
    List<BestAnnouncement> readAll();
}
