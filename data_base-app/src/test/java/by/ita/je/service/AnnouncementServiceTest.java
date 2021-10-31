package by.ita.je.service;

import by.ita.je.dao.AnnouncementDao;
import by.ita.je.dto.AnnouncementDto;
import by.ita.je.exception.IncorrectDataException;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.Announcement;
import by.ita.je.module.Car;
import by.ita.je.module.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AnnouncementServiceTest {
    @Mock
    private AnnouncementDao announcementDao;
    @InjectMocks
    private AnnouncementService announcementService;
private  static Announcement announcementGiven = Announcement.builder()
        .get_up(0)
         .numberPhone(1233)
        .build();

    List<Announcement> announcementList = new ArrayList<>();

    {
       announcementList.add(announcementGiven);

    }

    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this); }
    @Test
    void createAnnouncement_thenOk() {
        Mockito.when(announcementDao.save(announcementGiven)).thenReturn(announcementGiven);
        Announcement actual = announcementService.create(announcementGiven);
       Announcement expected = announcementGiven;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(announcementDao, Mockito.times(1)).save(announcementGiven);

    }
    @Test
    void createAnnouncement_thenException() {
       Announcement announcement  =Announcement.builder().numberPhone(0).build();
        IncorrectDataException incorrectDataException = Assertions
                .assertThrows(IncorrectDataException.class,()->announcementService.create(announcement));
        Assertions.assertEquals(incorrectDataException.getMessage(),
                "Введены некорректные данные для Announcement");
        Mockito.verify(announcementDao, Mockito.times(0)).save(Mockito.any());

    }
    @Test
    void updateAnnouncement_thenOk() {
        Announcement announcementGet = Announcement.builder()
                .numberPhone(1111)
                .build();
        Mockito.when(announcementDao.findById(1L)).thenReturn(Optional.ofNullable(announcementGiven));
        Mockito.when(announcementDao.save(announcementGiven)).thenReturn(announcementGet);
       Announcement actual = announcementService.update(1L, announcementGiven);
        Announcement expected = announcementGet;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(announcementDao, Mockito.times(1)).findById(1L);
        Mockito.verify(announcementDao, Mockito.times(1)).save(announcementGiven);
    }
    @Test
    void updateAnnouncement_thenOException() {

        Mockito.when(announcementDao.findById(4L)).thenReturn(Optional.empty());
        NoFoundEntityException noEntityException = Assertions.assertThrows( NoFoundEntityException.class,
                ()->announcementService.update(4L, announcementGiven));
        Assertions.assertEquals(noEntityException.getMessage(),
                "Такой записи для Announcement в базе данных не существует");
        Mockito.verify(announcementDao,Mockito.times(1)).findById(4L);
        Mockito.verify(announcementDao, Mockito.times(0)).save(Mockito.any());
    }
    @Test
    void readAllAnnouncements_thenOk() {
        Mockito.when(announcementDao.findAll()).thenReturn(announcementList);
        List<Announcement> actual = announcementService.readAll();
        List<Announcement> expected =announcementList;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(announcementDao, Mockito.times(1)).findAll();
    }

    @Test
    void readOneAnnoucement_thenOk() {
        Mockito.when(announcementDao.findById(1L)).thenReturn(Optional.ofNullable(announcementGiven));
      Announcement expected = announcementService.readOne(1L);
       Announcement actual =announcementGiven;
        Assertions.assertEquals(expected,actual);
        Mockito.verify(announcementDao, Mockito.times(1)).findById(1L);
    }
    @Test
    void readOneAnnoucement_thenException()  {
        Mockito.when(announcementDao.findById(11L)).thenReturn(Optional.empty());
        NoFoundEntityException runtimeException = Assertions.assertThrows(NoFoundEntityException.class,()->announcementService.readOne(11L));
        Assertions.assertEquals(runtimeException.getMessage(),
                "Такой записи для Announcement в базе данных не существует");
        Mockito.verify(announcementDao, Mockito.times(1)).findById(11L);

    }
    @Test
    void deleteAnnoncementById_thenOk() {
       announcementService.deleteById(1L);
        Mockito.verify(announcementDao, Mockito.times(1)).deleteById(1L);
    }
}