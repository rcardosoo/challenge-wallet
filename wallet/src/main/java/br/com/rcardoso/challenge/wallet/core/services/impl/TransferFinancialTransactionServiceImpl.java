package br.com.rcardoso.challenge.wallet.core.services.impl;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.domain.dto.FinancialMovementDto;
import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;
import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import br.com.rcardoso.challenge.wallet.core.services.ICreateFinancialMovementService;
import br.com.rcardoso.challenge.wallet.core.services.IFinancialTransactionService;
import br.com.rcardoso.challenge.wallet.core.services.factory.CreateFinancialMovementServiceFactory;
import br.com.rcardoso.challenge.wallet.dataprovider.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.rcardoso.challenge.wallet.core.enums.MovementType.CREDIT;
import static br.com.rcardoso.challenge.wallet.core.enums.MovementType.DEBIT;
import static br.com.rcardoso.challenge.wallet.core.enums.TransactionType.TRANSFER;

@Service
@RequiredArgsConstructor
public class TransferFinancialTransactionServiceImpl implements IFinancialTransactionService {

    private static final String TRANSFER_DEBIT_DEFAULT_DESCRIPTION = "Transferência efetuada";

    private static final String TRANSFER_CREDIT_DEFAULT_DESCRIPTION = "Transferência recebida";

    private final ITransactionRepository transactionRepository;

    private final IAccountService accountService;

    private final CreateFinancialMovementServiceFactory createFinancialMovementServiceFactory;

    @Override
    public Transaction createFinancialTransaction(TransactionDto transactionDto) {
        final var requestAccount = accountService.read(transactionDto.getRequesterAccount());
        final var destinationAccount = accountService.read(transactionDto.getDestinationAccount());

        final var transaction = transactionRepository.save(Transaction.builder()
                .createdAt(LocalDateTime.now())
                .transactionType(TRANSFER)
                .sourceAccount(requestAccount.toEntity())
                .destinationAccount(destinationAccount.toEntity())
                .originalValue(transactionDto.getValue())
                .build());

        final var debitFinancialMovementService
                = createFinancialMovementServiceFactory.getFinancialMovementService(DEBIT);

        final var creditFinancialMovementService
                = createFinancialMovementServiceFactory.getFinancialMovementService(CREDIT);

        createFinancialMovement(debitFinancialMovementService, transaction, TRANSFER_DEBIT_DEFAULT_DESCRIPTION);
        createFinancialMovement(creditFinancialMovementService, transaction, TRANSFER_CREDIT_DEFAULT_DESCRIPTION);

        return transaction;
    }

    private void createFinancialMovement(ICreateFinancialMovementService service, Transaction transaction, String description) {
        service.create(FinancialMovementDto.builder()
                .value(transaction.getOriginalValue())
                .description(description)
                .build(), transaction);
    }

    @Override
    public TransactionType getType() {
        return TRANSFER;
    }
}
