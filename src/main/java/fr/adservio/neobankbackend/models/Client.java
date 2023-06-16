package fr.adservio.neobankbackend.models;

import fr.adservio.neobankbackend.contants.ClientType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@EqualsAndHashCode(callSuper = true)
public class Client extends AbstractEntity
{
    @Enumerated(EnumType.STRING)
    @Column(name = "client_type" , length = 20)
    private ClientType clientType;

    @Column(name = "first_name" , length = 200, nullable = false)
    private String lastName;

    @Column(name = "last_name" , length = 200, nullable = true)
    private String firstName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "adress", length = 200)
    private String adress;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "telephone", length = 30)
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
