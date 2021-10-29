package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchDto {
   // private long id;
    private String nameCar;
    private String modelCar;
    private int price;
    private int yearOfIssue;
    private int milage;
    private  int volumeEngine;
    private String typeEngine;
    private String transmission;
    private String location;
    private String custom;
    private String exchange;

}
