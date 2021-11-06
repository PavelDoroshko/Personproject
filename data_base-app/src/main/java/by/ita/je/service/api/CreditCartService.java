package by.ita.je.service.api;

import by.ita.je.dao.CreditCartDao;
import by.ita.je.entity.CreditCart;
import by.ita.je.entity.User;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.service.InterfaseCreditCarService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CreditCartService implements InterfaseCreditCarService {

    private final CreditCartDao creditCartDao;


    public CreditCartService(CreditCartDao creditCartDao) {
        this.creditCartDao = creditCartDao;
    }


    @Transactional
    public CreditCart create(CreditCart creditCart) {
        return creditCartDao.save(creditCart);
    }

}
