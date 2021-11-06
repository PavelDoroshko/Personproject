package by.ita.je.service;

import by.ita.je.dao.CreditCartDao;
import by.ita.je.entity.CreditCart;
import by.ita.je.service.api.CreditCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CreditCartServiceTest {
    @Mock
    private CreditCartDao creditCartDao;
    @InjectMocks
    private CreditCartService creditCartService;

    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCreditCart_thenOk() {
        CreditCart creditCart = CreditCart.builder()
                .cash(1000)
                .build();
        Mockito.when(creditCartDao.save(creditCart)).thenReturn(creditCart);
        CreditCart actual = creditCartService.create(creditCart);
        CreditCart expected = creditCart;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(creditCartDao, Mockito.times(1)).save(creditCart);

    }
}