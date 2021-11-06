package by.ita.je.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private long id;
    private int get_up;
    private int numberPhone;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coment_id")
    @JsonManagedReference
    private Coment coment;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bestAnnouncement_id")
    @JsonManagedReference
    private BestAnnouncement bestAnnouncement;
}
