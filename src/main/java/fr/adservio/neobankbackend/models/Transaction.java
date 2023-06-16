package fr.adservio.neobankbackend.models;

import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractEntity {


    @Column(name = "transaction_reference", nullable = false, length = 100)
    private String transactionReference;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", length = 30)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", length = 30)
    private TransactionStatus transactionStatus;

    @NotEmpty
    @NotBlank
    @Column(name = "transaction_descritpion")
    private String transactionDescription;

    @NotEmpty
    @NotBlank
    @Column(name = "fund_source", length = 200)
    private String fundSource;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
