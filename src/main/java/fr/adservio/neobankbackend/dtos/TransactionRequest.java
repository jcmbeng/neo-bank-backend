package fr.adservio.neobankbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import fr.adservio.neobankbackend.models.Account;
import fr.adservio.neobankbackend.models.Transaction;
import fr.adservio.neobankbackend.models.User;
import fr.adservio.neobankbackend.utils.ReferenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionRequest {

    @NotEmpty
    private String accountNumber;

    @NotEmpty
    @NotBlank
    private BigDecimal amount;

    @NotEmpty
    @NotBlank
    private TransactionType transactionType;

    @NotEmpty
    @NotBlank
    private String transactionDescription;

    @NotEmpty
    @NotBlank
    private String fundSource;


    @JsonIgnore
    private TransactionStatus transactionStatus;

    @JsonIgnore
    private User createdBy;

    @JsonIgnore
    private Account account;

    public static Transaction fromRequestToEntity(final TransactionRequest request) {
        if (request == null) {
            return null;
        }

        final Transaction transaction = new Transaction();
        transaction.setAccount(request.getAccount());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionReference(ReferenceGenerator.generateReference());
        transaction.setTransactionStatus(request.getTransactionStatus());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setCreatedBy(request.getCreatedBy());
        transaction.setFundSource(request.getFundSource().trim());
        transaction.setTransactionDescription(request.getTransactionDescription().trim());
        return  transaction;
    }
}
