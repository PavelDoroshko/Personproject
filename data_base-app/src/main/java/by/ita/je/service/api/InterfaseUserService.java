package by.ita.je.service.api;

import javassist.NotFoundException;
import by.ita.je.module.User;

import java.util.List;

public interface InterfaseUserService {

    User create(User user);
   User readOne(Long id) throws NotFoundException;
    void deleteById(Long id);

    List<User> readAll();

    User update(Long id, User user) throws NotFoundException;
}
