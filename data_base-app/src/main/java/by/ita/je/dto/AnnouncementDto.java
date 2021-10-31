package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnnouncementDto {
    private long id;
    private int get_up;
    private int numberPhone;
    private CarDto car;
    private ComentDto coment;
    private UserDto user;

}
