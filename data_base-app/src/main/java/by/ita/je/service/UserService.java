package by.ita.je.service;

import by.ita.je.dao.UserDao;
import by.ita.je.exception.NatFoundException;
import javassist.NotFoundException;
import by.ita.je.module.User;
import org.springframework.stereotype.Service;
import by.ita.je.service.api.InterfaseUserService;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class UserService implements InterfaseUserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public User create(User user) {
        return userDao.save(user);
    }

    @Override
    public User readOne(Long id) throws NotFoundException {
        return userDao.findById(id).orElseThrow(() -> new NatFoundException("User"));
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<User> readAll() {
        final Spliterator<User> result = userDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }
    @Override
    public User update(Long id, User user) throws NotFoundException {
        User secondUser  = userDao.findById(id).orElseThrow(() -> new NotFoundException("NotFoundException Home"));
      secondUser.setBestAnnouncements(user.getBestAnnouncements());
      secondUser.setAnnouncementList(user.getAnnouncementList());
      secondUser.setBalance(user.getBalance());
      secondUser.setName(user.getName());
        return userDao.save(secondUser);
    }
}