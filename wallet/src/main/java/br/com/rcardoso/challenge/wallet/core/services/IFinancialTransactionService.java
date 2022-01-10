package br.com.rcardoso.challenge.wallet.core.services;

import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;
import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;

public interface IFinancialTransactionService {

    Transaction createFinancialTransaction(TransactionDto transactionDto);

    TransactionType getType();
}
