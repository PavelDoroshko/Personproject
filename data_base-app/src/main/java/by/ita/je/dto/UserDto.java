package by.ita.je.dto;

import by.ita.je.module.Announcement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private long user_id;
    private String name;
    private int balance;
    private List<BestAnnouncementDto> bestAnnouncements;
   private List<AnnouncementDto> announcementList;
private CreditCartDto creditCart;
}
