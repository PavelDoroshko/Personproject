package by.ita.je.service;

import by.ita.je.dao.ComentDao;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.Car;
import by.ita.je.module.Coment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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

    @Test
    void whenUpdateComment_returnComment() {
        Mockito.when(comentDAO.findById(1L)).thenReturn(Optional.ofNullable(comentGiven));
        Mockito.when(comentDAO.save(comentGiven)).thenReturn(comentGiven);
        Coment actual = comentService.update(1L, comentGiven);
        Coment expected = comentGiven;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(comentDAO, Mockito.times(1)).findById(1L);
        Mockito.verify(comentDAO, Mockito.times(1)).save(comentGiven);

    }

    @Test
    void whenUpdateComment_thenException() {
        Mockito.when(comentDAO.findById(4L)).thenReturn(Optional.empty());
        NoFoundEntityException noEntityException = Assertions.assertThrows(NoFoundEntityException.class,
                () -> comentService.update(4L, comentGiven));
        Assertions.assertEquals(noEntityException.getMessage(),
                "Такой записи для Coment в базе данных не существует");
        Mockito.verify(comentDAO, Mockito.times(1)).findById(4L);
        Mockito.verify(comentDAO, Mockito.times(0)).save(Mockito.any());


    }
}