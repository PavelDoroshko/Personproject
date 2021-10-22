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
@Table(name = "BEST_ANNOUNCEMENT")
public class BestAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne (cascade = CascadeType.ALL)

    private Announcement announcement;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="user_user_id")
    private User user;
}
