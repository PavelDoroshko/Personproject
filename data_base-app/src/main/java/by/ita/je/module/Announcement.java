package by.ita.je.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ANNOUNCEMENT")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int get_up;
    private  int numberPhone;

   // @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @OneToOne
    private Car car;



  // @ManyToOne(  cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH})
  //@JoinColumn(name="user_user_id")
    @ManyToOne( fetch = FetchType.EAGER)
   private User user;

   @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Coment  coment;

    @OneToOne(orphanRemoval = true,cascade = {CascadeType.ALL,CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BestAnnouncement bestAnnouncement;
}
