package fr.adservio.neobankbackend.services;

import fr.adservio.neobankbackend.contants.AccountStatus;
import fr.adservio.neobankbackend.contants.AccountType;
import fr.adservio.neobankbackend.dtos.AccountResponse;
import fr.adservio.neobankbackend.models.Account;

import java.math.BigDecimal;


public interface AccountService extends  AbStractService<AccountResponse, Long>
{

    AccountResponse findByAccountNumber(String accountNumber);

    AccountResponse changeAccountType(Long id, AccountType accountType);

    AccountResponse changeAccountStatus(Long id, AccountStatus accountStatus);

    boolean withdraw(Account account, BigDecimal amount);

    boolean deposit(Account account, BigDecimal amount);

}
