package by.ita.je.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int get_up;
    private  int numberPhone;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Car car;

    @ManyToOne(   cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="user_user_id")
    private User user;

   @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Coment  coment;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private BestAnnouncement bestAnnouncement;
}
