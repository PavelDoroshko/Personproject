package by.ita.je.dao;

import by.ita.je.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByLogin(String login);

}
