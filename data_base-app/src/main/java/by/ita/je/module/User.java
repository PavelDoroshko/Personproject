package by.ita.je.module;

import by.ita.je.module.Announcement;
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
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String name;
    private int balance;
    private int pasword;

    @OneToMany( orphanRemoval = true, cascade ={ CascadeType.ALL},mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Announcement> announcementList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BestAnnouncement> bestAnnouncements;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private CreditCart creditCart;
}
