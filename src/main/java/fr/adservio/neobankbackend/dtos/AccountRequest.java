package fr.adservio.neobankbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.adservio.neobankbackend.contants.AccountStatus;
import fr.adservio.neobankbackend.contants.AccountType;
import fr.adservio.neobankbackend.models.Account;
import fr.adservio.neobankbackend.models.Client;
import fr.adservio.neobankbackend.models.Transaction;
import fr.adservio.neobankbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequest
{
    private AccountStatus accountStatus;

    private AccountType accountType;

    private Long clientId;

    @JsonIgnore
    private Client client;

    @JsonIgnore
    private User createdBy;

    public static Account fromRequestToEntity(final AccountRequest request)
    {
        if(request == null)
        {
            return null;
        }

        final Account account = new Account();

        account.setAccountBalance(BigDecimal.valueOf(0));
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setAccountType(request.getAccountType());
        account.setAccountStatus(AccountStatus.OPENED);
        account.setCreatedBy(request.getCreatedBy());
        account.setClient(request.getClient());
        return account;

    }
}
