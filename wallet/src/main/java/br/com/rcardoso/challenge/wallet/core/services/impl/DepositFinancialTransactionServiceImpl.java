package br.com.rcardoso.challenge.wallet.core.services.impl;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import br.com.rcardoso.challenge.wallet.core.domain.FinancialMovement;
import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.domain.dto.FinancialMovementDto;
import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;
import br.com.rcardoso.challenge.wallet.core.enums.MovementType;
import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import br.com.rcardoso.challenge.wallet.core.services.IFinancialTransactionService;
import br.com.rcardoso.challenge.wallet.core.services.factory.CreateFinancialMovementServiceFactory;
import br.com.rcardoso.challenge.wallet.dataprovider.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.rcardoso.challenge.wallet.core.enums.MovementType.CREDIT;
import static br.com.rcardoso.challenge.wallet.core.enums.TransactionType.DEPOSIT;

@Service
@RequiredArgsConstructor
public class DepositFinancialTransactionServiceImpl implements IFinancialTransactionService {

    private static final String DEPOSIT_DEFAULT_DESCRIPTION = "Depósito efetuado";

    private final ITransactionRepository transactionRepository;

    private final IAccountService accountService;

    private final CreateFinancialMovementServiceFactory createFinancialMovementServiceFactory;

    @Override
    public Transaction createFinancialTransaction(TransactionDto transactionDto) {
        final var account = accountService.read(transactionDto.getRequesterAccount());

        final var transaction = transactionRepository.save(Transaction.builder()
                .createdAt(LocalDateTime.now())
                .transactionType(DEPOSIT)
                .destinationAccount(Account.builder()
                        .id(account.getId())
                        .build())
                .originalValue(transactionDto.getValue())
                .build());

        final var financialMovementService
                = createFinancialMovementServiceFactory.getFinancialMovementService(CREDIT);

        financialMovementService.create(FinancialMovementDto.builder()
                .value(transaction.getOriginalValue())
                .description(DEPOSIT_DEFAULT_DESCRIPTION)
                .build(), transaction);

        return transaction;
    }

    @Override
    public TransactionType getType() {
        return DEPOSIT;
    }
}
