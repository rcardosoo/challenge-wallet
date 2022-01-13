package br.com.rcardoso.challenge.wallet.core.services.impl;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.domain.dto.FinancialMovementDto;
import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;
import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import br.com.rcardoso.challenge.wallet.core.services.IFinancialTransactionService;
import br.com.rcardoso.challenge.wallet.core.services.factory.CreateFinancialMovementServiceFactory;
import br.com.rcardoso.challenge.wallet.dataprovider.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.rcardoso.challenge.wallet.core.enums.MovementType.DEBIT;
import static br.com.rcardoso.challenge.wallet.core.enums.TransactionType.PAYMENT;
import static br.com.rcardoso.challenge.wallet.core.enums.TransactionType.WITHDRAW;

@Service
@RequiredArgsConstructor
public class PaymentFinancialTransactionServiceImpl implements IFinancialTransactionService {

    private static final String WITHDRAW_DEFAULT_DESCRIPTION = "Pagamento efetuado";

    private final ITransactionRepository transactionRepository;

    private final IAccountService accountService;

    private final CreateFinancialMovementServiceFactory createFinancialMovementServiceFactory;

    @Override
    public Transaction createFinancialTransaction(TransactionDto transactionDto) {
        final var account = accountService.read(transactionDto.getRequesterAccount());

        final var transaction = transactionRepository.save(Transaction.builder()
                .createdAt(LocalDateTime.now())
                .transactionType(PAYMENT)
                .sourceAccount(account.toEntity())
                .originalValue(transactionDto.getValue())
                .build());

        final var financialMovementService
                = createFinancialMovementServiceFactory.getFinancialMovementService(DEBIT);

        financialMovementService.create(FinancialMovementDto.builder()
                .value(transaction.getOriginalValue())
                .description(WITHDRAW_DEFAULT_DESCRIPTION)
                .build(), transaction);

        return transaction;
    }

    @Override
    public TransactionType getType() {
        return PAYMENT;
    }
}
