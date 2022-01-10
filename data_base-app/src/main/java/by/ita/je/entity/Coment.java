package by.ita.je.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "coment")
public class Coment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coment_id")
    private long id;
    private String message;

    @OneToOne(mappedBy = "coment",cascade = CascadeType.ALL)
    @JsonBackReference
   private Announcement announcement;
}