package fr.adservio.neobankbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.adservio.neobankbackend.contants.AccountStatus;
import fr.adservio.neobankbackend.contants.AccountType;
import fr.adservio.neobankbackend.models.Account;
import fr.adservio.neobankbackend.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountResponse {
    private Long id;

    private boolean active;

    private AccountStatus accountStatus;

    private String accountNumber;

    private AccountType accountType;

    private ClientResponse client;

    private BigDecimal accountBalance;


    public static AccountResponse fromEntityToResponse(final Account account) {
        if (account == null) {
            return null;
        }

        return AccountResponse.builder()
                .id(account.getId())
                .accountBalance(account.getAccountBalance())
                .active(account.isActive())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .accountStatus(account.getAccountStatus())
                .client(ClientResponse.fromEntityToResponse(account.getClient()))
                .build();

    }
}
