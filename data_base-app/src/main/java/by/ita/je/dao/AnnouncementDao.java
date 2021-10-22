package by.ita.je.dao;

import by.ita.je.module.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementDao extends CrudRepository<Announcement,Long> {
}
