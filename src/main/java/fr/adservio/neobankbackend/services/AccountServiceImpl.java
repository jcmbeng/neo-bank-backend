package fr.adservio.neobankbackend.services;

import fr.adservio.neobankbackend.contants.AccountStatus;
import fr.adservio.neobankbackend.contants.AccountType;
import fr.adservio.neobankbackend.dtos.AccountRequest;
import fr.adservio.neobankbackend.dtos.AccountResponse;
import fr.adservio.neobankbackend.exceptions.EntityNotFoundException;
import fr.adservio.neobankbackend.models.Account;
import fr.adservio.neobankbackend.models.Client;
import fr.adservio.neobankbackend.models.User;
import fr.adservio.neobankbackend.repositories.AccountRepository;
import fr.adservio.neobankbackend.repositories.ClientRepository;
import fr.adservio.neobankbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    @Override
    public AccountResponse findById(Long id) {
        return accountRepository.findById(id).map(AccountResponse::fromEntityToResponse).orElseThrow(
                () -> new EntityNotFoundException("Impossible de trouver le Compte avec l'ID " + id)
        );
    }

    @Override
    public void delete(Long id) {
        Optional<Account> accountToSearch = accountRepository.findById(id);
        if (accountToSearch.isPresent()) {
            Account account = accountToSearch.get();
            account.setDeleted(true);
            accountRepository.save(account);
        }
    }

    @Override
    public AccountResponse active(Long id, boolean active) {

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setActive(active);
            account = accountRepository.save(account);
            return AccountResponse.fromEntityToResponse(account);
        } else {
            throw new EntityNotFoundException("Impossible de trouver un compte avec l'ID :" + id);
        }
    }

    @Override
    public List<AccountResponse> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountResponse::fromEntityToResponse)
                .toList();
    }

    @Override
    public AccountResponse save(Object frontRequest) {
        AccountRequest request = (AccountRequest) frontRequest;
        Optional<User> user = userRepository.findById(Long.valueOf(2));
        if (user.isPresent()) {
            request.setCreatedBy(user.get());
        }

        Optional<Client> optionalClient = clientRepository.findById(request.getClientId());
        if (optionalClient.isPresent()) {
            request.setClient(optionalClient.get());
        }
        return AccountResponse.fromEntityToResponse(
                accountRepository.save(
                        AccountRequest.fromRequestToEntity(request)
                )
        );
    }

    @Override
    public AccountResponse findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumberIgnoreCase(accountNumber).map(AccountResponse::fromEntityToResponse).orElseThrow(
                () -> new EntityNotFoundException("Impossible de trouver le Compte avec le Numéro " + accountNumber)
        );
    }

    @Override
    public AccountResponse changeAccountType(Long id, AccountType accountType) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            Optional<User> user = userRepository.findById(Long.valueOf(2));

            if (user.isPresent()) {
                account.setUpdatedBy(user.get());
            }

            account.setAccountType(accountType);
            accountRepository.save(account);
            return AccountResponse.fromEntityToResponse(account);
        }
        return null;
    }

    @Override
    public AccountResponse changeAccountStatus(Long id, AccountStatus accountStatus) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            Optional<User> user = userRepository.findById(Long.valueOf(2));

            if (user.isPresent()) {
                account.setUpdatedBy(user.get());
            }

            account.setAccountStatus(accountStatus);
            accountRepository.save(account);
        }
        return null;
    }

    @Override
    public boolean withdraw(Account account, BigDecimal amount) {
        if (account.isActive() && account.getAccountStatus().equals(AccountStatus.OPENED)) {
            BigDecimal zero = BigDecimal.ZERO;
            BigDecimal accountNewBalance = account.getAccountBalance().subtract(amount);

            if (accountNewBalance.compareTo(zero) > 0) {
                account.setAccountBalance(accountNewBalance);
                accountRepository.save(account);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deposit(Account account, BigDecimal amount) {
        if (account.isActive() && account.getAccountStatus() == AccountStatus.OPENED) {
            BigDecimal accountNewBalance = account.getAccountBalance().add(amount);
            account.setAccountBalance(accountNewBalance);
            accountRepository.save(account);
            return true;
        }
        return false;
    }
}
