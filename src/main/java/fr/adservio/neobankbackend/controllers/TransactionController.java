package fr.adservio.neobankbackend.controllers;

import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import fr.adservio.neobankbackend.controllers.apis.TransactionApi;
import fr.adservio.neobankbackend.dtos.TransactionRequest;
import fr.adservio.neobankbackend.dtos.TransactionResponse;
import fr.adservio.neobankbackend.services.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionApi {

    private final TransactionServiceImpl transactionService;

    @Override
    public ResponseEntity<TransactionResponse> findById(Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @Override
    public ResponseEntity<TransactionResponse> save(TransactionRequest request) {
        return ResponseEntity.ok(transactionService.save(request));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseEntity<TransactionResponse> active(Long id, boolean active) {
        return ResponseEntity.ok(transactionService.active(id, active));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @Override
    public ResponseEntity<TransactionResponse> findByReference(String transactionReference) {
        return ResponseEntity.ok(transactionService.findByReference(transactionReference));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> findByTransaction(String accountNumber) {
        return ResponseEntity.ok(transactionService.findByAccountNumber(accountNumber));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> findByTransactionAndDate(String accountNumber, Date date) {
        return ResponseEntity.ok(transactionService.findByAccountAndDate(accountNumber, date));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> findByTransactionAndStatus(String accountNumber, TransactionStatus transactionStatus) {
        return ResponseEntity.ok(transactionService.findByAccountAndStatus(accountNumber, transactionStatus));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> findByTransactionAndType(String accountNumber, TransactionType transactionType) {
        return ResponseEntity.ok(transactionService.findByAccountAndType(accountNumber, transactionType));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> findByType(TransactionType transactionType) {
        return ResponseEntity.ok(transactionService.findByType(transactionType));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> findByStatus(TransactionStatus transactionStatustransactionStatus) {
        return ResponseEntity.ok(transactionService.findByStatus(transactionStatustransactionStatus));
    }
}
