package fr.adservio.neobankbackend.services;

import fr.adservio.neobankbackend.contants.TransactionStatus;
import fr.adservio.neobankbackend.contants.TransactionType;
import fr.adservio.neobankbackend.dtos.TransactionResponse;

import java.util.Date;
import java.util.List;

public interface TransactionService extends  AbStractService<TransactionResponse, Long>
{
    TransactionResponse findByReference(String transactionReference);

    List<TransactionResponse> findByAccountNumber(String accountNumber);

    List<TransactionResponse> findByAccountAndDate(String accountNumber, Date date);

    List<TransactionResponse> findByAccountAndStatus(String accountNumber, TransactionStatus transactionStatus);

    List<TransactionResponse> findByAccountAndType(String accountNumber, TransactionType transactionType);

    List<TransactionResponse> findByType(TransactionType transactionType);

    List<TransactionResponse> findByStatus(TransactionStatus transactionStatus);


}
