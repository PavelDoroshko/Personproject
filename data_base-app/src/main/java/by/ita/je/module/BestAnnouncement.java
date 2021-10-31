package by.ita.je.module;

import by.ita.je.module.Announcement;
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
@Table(name = "BEST_ANNOUNCEMENT")
public class BestAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   // @OneToOne (orphanRemoval = true,cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    //@OnDelete(action = OnDeleteAction.CASCADE)
   // @JsonIgnore
   @OneToOne(orphanRemoval = true,cascade = {CascadeType.ALL,CascadeType.REMOVE})
   @OnDelete(action = OnDeleteAction.CASCADE)
//   @OneToOne(mappedBy = "bestAnnouncement")
    private Announcement announcement;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
}
