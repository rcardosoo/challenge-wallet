package br.com.rcardoso.challenge.wallet.core.services.impl;

import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;
import br.com.rcardoso.challenge.wallet.core.services.ITransactionService;
import br.com.rcardoso.challenge.wallet.core.services.factory.FinancialTransactionServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final FinancialTransactionServiceFactory transactionFactory;

    @Override
    public TransactionDto create(TransactionDto transactionDto) {

        final var transactionService =
                transactionFactory.getFinancialTransactionService(transactionDto.getTransactionType());

        final var transaction = transactionService.createFinancialTransaction(transactionDto);

        return new TransactionDto(transaction);
    }
}
