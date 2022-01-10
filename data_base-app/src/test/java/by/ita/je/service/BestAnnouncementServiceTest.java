package by.ita.je.service;

import by.ita.je.dao.BestAnnouncementDao;
import by.ita.je.entity.BestAnnouncement;
import by.ita.je.service.api.BestAnnouncementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class BestAnnouncementServiceTest {
    @Mock
    private BestAnnouncementDao bestAnnouncementDao;
    @InjectMocks
    private BestAnnouncementService bestAnnouncementService;

    private static BestAnnouncement bestAnnouncementGiven = BestAnnouncement.builder()
            .build();

    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        Mockito.when(bestAnnouncementDao.save(bestAnnouncementGiven)).thenReturn(bestAnnouncementGiven);
        BestAnnouncement actual = bestAnnouncementService.create(bestAnnouncementGiven);
        BestAnnouncement expected = bestAnnouncementGiven;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(bestAnnouncementDao, Mockito.times(1)).save(bestAnnouncementGiven);
    }
}