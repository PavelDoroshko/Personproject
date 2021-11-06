package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    public long id;
    private String login;
    private int balance;
    private int pasword;
    private List<BestAnnouncementDto> bestAnnouncements;
    private List<AnnouncementDto> announcementList;
    private CreditCartDto creditCart;
}
