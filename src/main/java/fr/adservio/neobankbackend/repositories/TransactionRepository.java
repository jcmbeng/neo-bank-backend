package fr.adservio.neobankbackend.repositories;

import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import fr.adservio.neobankbackend.models.Account;
import fr.adservio.neobankbackend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    Optional<Transaction> findByTransactionReferenceIgnoreCase(String transactionReference);

    List<Transaction> findByAccount(Account account);

    List<Transaction> findByAccountAndCreateDateContaining(Account account, Date date);

    List<Transaction> findByAccountAndTransactionStatus(Account account, TransactionStatus transactionStatus);

    List<Transaction> findByAccountAndTransactionType(Account account, TransactionType transactionType);

    List<Transaction> findByTransactionType(TransactionType transactionType);

    List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);

}
