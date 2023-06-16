package fr.adservio.neobankbackend.controllers.apis;

import fr.adservio.neobankbackend.contants.AccountStatus;
import fr.adservio.neobankbackend.contants.AccountType;
import fr.adservio.neobankbackend.dtos.AccountRequest;
import fr.adservio.neobankbackend.dtos.AccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/neo/v1/accounts")
public interface AccountApi
{
    @GetMapping("/{id}")
    ResponseEntity<AccountResponse> findById(@PathVariable(name = "id") Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id);

    @PatchMapping("/{id}/{active}")
    void active(@PathVariable(name = "id") Long id,
                @PathVariable(name = "active") boolean active);

    @GetMapping
    ResponseEntity<List<AccountResponse>> findAll();

    @PostMapping
    ResponseEntity<AccountResponse> save(@RequestBody AccountRequest request);


    @GetMapping("by-account-number/{accountNumber}")
    ResponseEntity<AccountResponse> findByAccountNumber(@PathVariable(name = "accountNumber") String accountNumber);

    @PatchMapping("set-account-type/{id}/{accountType}")
    ResponseEntity<AccountResponse> changeAccountType(@PathVariable(name = "id") Long id,
                                                      @PathVariable(name = "accountType") AccountType accountType);

    @PatchMapping("set-account-status/{id}/{accountStatus}")
    ResponseEntity<AccountResponse> changeAccountStatus(@PathVariable(name = "id")  Long id,
                                                        @PathVariable(name = "accountStatus") AccountStatus accountStatus);


    @GetMapping("/bonjour")
    String bonjour();


}
