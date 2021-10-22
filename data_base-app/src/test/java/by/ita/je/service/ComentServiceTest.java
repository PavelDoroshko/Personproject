package by.ita.je.service;

import by.ita.je.dao.ComentDao;
import by.ita.je.module.Coment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ComentServiceTest {
    @Mock
    private ComentDao comentDAO;
    @InjectMocks
    private ComentService comentService;

    private static Coment comentGiven = Coment.builder()
            .message("good_good")
            .build();

    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void whenCreate_returnComent() {
        Mockito.when(comentDAO.save(comentGiven)).thenReturn(comentGiven);
       Coment actual = comentService.create(comentGiven);
       Coment expected = comentGiven;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(comentDAO, Mockito.times(1)).save(comentGiven);
    }
}