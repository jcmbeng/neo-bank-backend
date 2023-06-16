package fr.adservio.neobankbackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import fr.adservio.neobankbackend.models.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponse {

    private Long id;
    private String accountName;
    private BigDecimal amount;
    private String accountNumber;
    private String transactionReference;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private String transactionDescription;
    private String fundSource;

    @Temporal(TemporalType.DATE)
    protected Instant createDate;

    public static TransactionResponse fromEntityToResponse(final Transaction transaction) {

        if (transaction == null) {
            return null;
        }

        final String accountName = transaction.getAccount().getClient().getLastName() + " " +
                transaction.getAccount().getClient().getFirstName();

        return TransactionResponse.builder()
                .id(transaction.getId())
                .accountName(accountName)
                .accountNumber(transaction.getAccount().getAccountNumber())
                .amount(transaction.getAmount())
                .transactionReference(transaction.getTransactionReference())
                .transactionStatus(transaction.getTransactionStatus())
                .transactionType(transaction.getTransactionType())
                .createDate(transaction.getCreateDate())
                .fundSource(transaction.getFundSource())
                .transactionDescription(transaction.getTransactionDescription())
                .build();
    }
}
