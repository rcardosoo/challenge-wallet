package br.com.rcardoso.challenge.wallet.core.services;

import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;

public interface ITransactionService {

    TransactionDto create(TransactionDto transactionDto);
}
