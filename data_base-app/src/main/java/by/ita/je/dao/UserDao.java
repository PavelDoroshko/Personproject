package by.ita.je.dao;

import by.ita.je.module.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long> {
}
