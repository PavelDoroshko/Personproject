package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.Car;
import by.ita.je.module.User;
import by.ita.je.service.api.InterfaseUserService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service

public class UserService implements InterfaseUserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public User create(User user) throws IncorrectDataException {
        if ((user.getName() == ""))
            throw new IncorrectDataException("User");
        user.setBalance(0);
       if( user.getPasword()==0)
           throw new IncorrectDataException("User");
        return userDao.save(user);
    }

    @Override
    public User readOne(Long id) throws NotFoundException,IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("User");
         final User user =userDao.findById(id).orElseThrow(() -> new NoFoundEntityException("User"));
   return user;
    }

    @Override
    public void deleteById(Long id) throws NotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("User");
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            throw new NoFoundEntityException("User");
        }

    }
    @Override
    public List<User> readAll() {
        final Spliterator<User> result = userDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }

}