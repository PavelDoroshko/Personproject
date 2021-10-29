package by.ita.je.service.api;

import by.ita.je.exception.IncorrectDataException;
import javassist.NotFoundException;
import by.ita.je.module.User;

import java.util.List;

public interface InterfaseUserService {

    User create(User user) throws IncorrectDataException;

    User readOne(Long id) throws NotFoundException,IncorrectDataException;

    void deleteById(Long id) throws NotFoundException, IncorrectDataException;

    List<User> readAll();
    User readOneByLogin(String login);
}
