package by.ita.je.dto;

import by.ita.je.module.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BestAnnouncementDto {
    private long id;
    private AnnouncementDto announcement;
    //private User user;

}
