package by.ita.je.service;

import by.ita.je.dao.CreditCartDao;
import by.ita.je.module.CreditCart;
import by.ita.je.module.User;
import by.ita.je.service.api.InterfaseCreditCarService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreditCartService implements InterfaseCreditCarService {

    private CreditCartDao creditCartDao;


    public CreditCartService(CreditCartDao creditCartDao) {
        this.creditCartDao = creditCartDao;
    }


    @Transactional
    public CreditCart create(CreditCart creditCart) {
        return creditCartDao.save(creditCart);
    }


}
