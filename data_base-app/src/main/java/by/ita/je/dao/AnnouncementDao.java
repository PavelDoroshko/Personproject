package by.ita.je.dao;

import by.ita.je.entity.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementDao extends CrudRepository<Announcement,Long> {
}
