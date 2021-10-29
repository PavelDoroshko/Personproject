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
    public long user_id;
    private String login;
    private int balance;
    private int pasword;
    private List<BestAnnouncementDto> bestAnnouncements;
   private List<AnnouncementDto> announcementList;
private CreditCartDto creditCart;
}
