package by.ita.je.dto;

import by.ita.je.module.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCartDto {
    private long id;
    private int cash;
    private User user;

}
