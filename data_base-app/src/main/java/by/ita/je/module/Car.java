package by.ita.je.module;

import by.ita.je.module.Announcement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

    @OneToOne(cascade = CascadeType.ALL)
     private Announcement announcement;
}
