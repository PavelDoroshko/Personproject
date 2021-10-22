package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComentDto {
    private long id;
    private String message;
    private AnnouncementDto announcement;

}
