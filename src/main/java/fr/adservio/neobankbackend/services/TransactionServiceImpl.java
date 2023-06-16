package fr.adservio.neobankbackend.services;

import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import fr.adservio.neobankbackend.dtos.TransactionRequest;
import fr.adservio.neobankbackend.dtos.TransactionResponse;
import fr.adservio.neobankbackend.exceptions.EntityNotFoundException;
import fr.adservio.neobankbackend.models.Account;
import fr.adservio.neobankbackend.models.Transaction;
import fr.adservio.neobankbackend.models.User;
import fr.adservio.neobankbackend.repositories.AccountRepository;
import fr.adservio.neobankbackend.repositories.TransactionRepository;
import fr.adservio.neobankbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountServiceImpl accountService;

    @Override
    public TransactionResponse findById(Long id) {
        return transactionRepository.findById(id).map(TransactionResponse::fromEntityToResponse).orElseThrow(
                () -> new EntityNotFoundException("Impossible de trouver le Un transaction avec l'ID " + id)
        );
    }

    @Override
    public void delete(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setDeleted(true);
            transactionRepository.save(transaction);
        }
    }

    @Override
    public TransactionResponse active(Long id, boolean active) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setActive(true);
            transactionRepository.save(transaction);
            return TransactionResponse.fromEntityToResponse(transaction);
        }
        else
        {
            throw new EntityNotFoundException("Impossible de trouver la trasaction avec l'ID " +id);
        }
    }

    @Override
    public List<TransactionResponse> findAll() {
        return transactionRepository
                .findAll()
                .stream()
                .map(TransactionResponse::fromEntityToResponse)
                .toList();
    }

    @Override
    public TransactionResponse save(Object frontRequest) {
        TransactionRequest request = (TransactionRequest) frontRequest;

        Optional<User> user = userRepository.findById(Long.valueOf(2));
        if(user.isPresent())
        {
            request.setCreatedBy(user.get());
        }

        Optional<Account> optionalAccount = accountRepository.findByAccountNumberIgnoreCase(request.getAccountNumber());

        if (optionalAccount.isPresent()) {
            request.setAccount(optionalAccount.get());
        }


        boolean transactionResult = request.getTransactionType() == TransactionType.CREDIT ?
                accountService.deposit(request.getAccount(), request.getAmount()) :
                accountService.withdraw(request.getAccount(), request.getAmount());

        request.setTransactionStatus( transactionResult ? TransactionStatus.SUCCESS : TransactionStatus.FAILED);

        return TransactionResponse.fromEntityToResponse(
                transactionRepository.save(
                        TransactionRequest.fromRequestToEntity(request)
                )
        );
    }

    @Override
    public TransactionResponse findByReference(String transactionReference) {
        return transactionRepository
                .findByTransactionReferenceIgnoreCase(transactionReference)
                .map(TransactionResponse::fromEntityToResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("Impossible de trouver le Transaction avec la référence " + transactionReference)
                );
    }

    @Override
    public List<TransactionResponse> findByAccountNumber(String accountNumber) {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionResponse::fromEntityToResponse)
                .toList();
    }

    @Override
    public List<TransactionResponse> findByAccountAndDate(String accountNumber, Date date) {

        Optional<Account> optionalAccount = accountRepository.findByAccountNumberIgnoreCase(accountNumber);

        if (optionalAccount.isPresent()) {
            return transactionRepository.findByAccountAndCreateDateContaining(optionalAccount.get(), date)
                    .stream()
                    .map(TransactionResponse::fromEntityToResponse)
                    .toList();
        }

        return new ArrayList<>();
    }

    @Override
    public List<TransactionResponse> findByAccountAndStatus(String accountNumber, TransactionStatus transactionStatus) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumberIgnoreCase(accountNumber);

        if (optionalAccount.isPresent()) {
            return transactionRepository.findByAccountAndTransactionStatus(optionalAccount.get(), transactionStatus)
                    .stream()
                    .map(TransactionResponse::fromEntityToResponse)
                    .toList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionResponse> findByAccountAndType(String accountNumber, TransactionType transactionType) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumberIgnoreCase(accountNumber);

        if (optionalAccount.isPresent()) {
            return transactionRepository.findByAccountAndTransactionType(optionalAccount.get(), transactionType)
                    .stream()
                    .map(TransactionResponse::fromEntityToResponse)
                    .toList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionResponse> findByType(TransactionType transactionType) {
        return transactionRepository.findByTransactionType(transactionType)
                .stream()
                .map(TransactionResponse::fromEntityToResponse)
                .toList();
    }

    @Override
    public List<TransactionResponse> findByStatus(TransactionStatus transactionStatus) {
        return transactionRepository.findByTransactionStatus(transactionStatus)
                .stream()
                .map(TransactionResponse::fromEntityToResponse)
                .toList();
    }
}
