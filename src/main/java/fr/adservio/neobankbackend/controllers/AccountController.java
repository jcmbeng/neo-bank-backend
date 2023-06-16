package fr.adservio.neobankbackend.controllers;

import fr.adservio.neobankbackend.contants.AccountStatus;
import fr.adservio.neobankbackend.contants.AccountType;
import fr.adservio.neobankbackend.controllers.apis.AccountApi;
import fr.adservio.neobankbackend.dtos.AccountRequest;
import fr.adservio.neobankbackend.dtos.AccountResponse;
import fr.adservio.neobankbackend.services.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountServiceImpl accountService;

    @Override
    public ResponseEntity<AccountResponse> findById(Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public void active(Long id, boolean active) {
        accountService.active(id, active);
    }

    @Override
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @Override
    public ResponseEntity<AccountResponse> save(AccountRequest request) {
        return ResponseEntity.ok(accountService.save(request));
    }

    @Override
    public ResponseEntity<AccountResponse> findByAccountNumber(String accountNumber) {
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
    }

    @Override
    public ResponseEntity<AccountResponse> changeAccountType(Long id, AccountType accountType) {
        return ResponseEntity.ok(accountService.changeAccountType(id, accountType));
    }

    @Override
    public ResponseEntity<AccountResponse> changeAccountStatus(Long id, AccountStatus accountStatus) {
        return ResponseEntity.ok(accountService.changeAccountStatus(id, accountStatus));
    }

    @Override
    public String bonjour() {
        return "Bonjour les amis comment allez-vous ???";
    }
}
