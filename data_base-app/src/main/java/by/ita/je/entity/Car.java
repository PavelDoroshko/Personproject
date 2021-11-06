package by.ita.je.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
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

    @OneToOne(mappedBy = "car",cascade = CascadeType.ALL)
    @JsonBackReference
     private Announcement announcement;
}
