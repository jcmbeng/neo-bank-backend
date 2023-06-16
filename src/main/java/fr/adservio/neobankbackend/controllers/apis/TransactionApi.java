package fr.adservio.neobankbackend.controllers.apis;

import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import fr.adservio.neobankbackend.dtos.TransactionRequest;
import fr.adservio.neobankbackend.dtos.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/neo/v1/transactions")
public interface TransactionApi
{
    @GetMapping("/{id}")
    ResponseEntity<TransactionResponse> findById(@PathVariable(name = "id") Long id);

    @PostMapping
    ResponseEntity<TransactionResponse> save(@RequestBody TransactionRequest request);


    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id);

    @PatchMapping("/{id}/{active}")
    ResponseEntity<TransactionResponse> active(@PathVariable(name = "id") Long id,
                @PathVariable(name = "active") boolean active);

    @GetMapping
    ResponseEntity<List<TransactionResponse>> findAll();

    @GetMapping("/by-transaction-reference/{transactionReference}")
    ResponseEntity<TransactionResponse> findByReference(@PathVariable(name = "transactionReference") String transactionReference);

    @GetMapping("/account/{accountNumber}")
    ResponseEntity<List<TransactionResponse>> findByTransaction(@PathVariable(name = "accountNumber") String accountNumber);

    @GetMapping("/account/{accountNumber}/date/{date}")
    ResponseEntity<List<TransactionResponse>> findByTransactionAndDate(
            @PathVariable(name = "accountNumber")String accountNumber,
            @PathVariable(name = "transactionStatus") Date date);

    @GetMapping("/account/{accountNumber}/status/{transactionStatus}")
    ResponseEntity<List<TransactionResponse>> findByTransactionAndStatus(
            @PathVariable(name = "accountNumber") String accountNumber,
            @PathVariable(name = "transactionStatus") TransactionStatus transactionStatus);

    @GetMapping("/account/{accountNumber}/type/{transactionType}")
    ResponseEntity<List<TransactionResponse>> findByTransactionAndType(
            @PathVariable(name = "accountNumber") String accountNumber,
            @PathVariable(name = "transactionType") TransactionType transactionType);

    @GetMapping("/type/{transactionType}")
    ResponseEntity<List<TransactionResponse>> findByType(
            @PathVariable(name = "transactionType")
            TransactionType transactionType);

    @GetMapping("/status/{transactionStatus}")
    ResponseEntity<List<TransactionResponse>> findByStatus(
            @PathVariable(name = "transactionStatus")
            TransactionStatus transactionStatustransactionStatus);
}
