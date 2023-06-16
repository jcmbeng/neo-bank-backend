package fr.adservio.neobankbackend.models;

import fr.adservio.neobankbackend.contants.AccountStatus;
import fr.adservio.neobankbackend.contants.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", length = 10)
    private AccountStatus accountStatus;

    @Column(name = "account_number", unique = true, nullable = false, length = 50)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "acocunt_type", length = 30)
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

}
