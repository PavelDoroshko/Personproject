package by.ita.je.module;

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
@Table(name = "CREDIT_CART")
public class CreditCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private int cash;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;
}
