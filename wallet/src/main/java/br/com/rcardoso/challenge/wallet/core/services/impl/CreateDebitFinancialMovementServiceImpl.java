package br.com.rcardoso.challenge.wallet.core.services.impl;

import br.com.rcardoso.challenge.wallet.core.domain.FinancialMovement;
import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.domain.dto.FinancialMovementDto;
import br.com.rcardoso.challenge.wallet.core.enums.MovementType;
import br.com.rcardoso.challenge.wallet.core.exceptions.NoBalanceAvailableException;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import br.com.rcardoso.challenge.wallet.core.services.ICreateFinancialMovementService;
import br.com.rcardoso.challenge.wallet.dataprovider.IFinancialMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.rcardoso.challenge.wallet.core.enums.MovementType.DEBIT;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
public class CreateDebitFinancialMovementServiceImpl implements ICreateFinancialMovementService {

    private final IFinancialMovementRepository financialMovementRepository;

    private final IAccountService accountService;

    @Override
    @Transactional(isolation=REPEATABLE_READ)
    public void create(FinancialMovementDto financialMovementDto, Transaction transaction) {

        final var balance = accountService.getBalance(transaction.getSourceAccount().getId());

        if (balance.compareTo(financialMovementDto.getValue()) < 0) {
            throw new NoBalanceAvailableException();
        }

        var account = transaction.getSourceAccount();
        account.setBalance(balance.subtract(financialMovementDto.getValue()));
        accountService.updateAccount(account);

        financialMovementRepository.save(FinancialMovement.builder()
                .description(financialMovementDto.getDescription())
                .movementType(DEBIT)
                .transaction(transaction)
                .value(financialMovementDto.getValue())
                .build());
    }

    @Override
    public MovementType getType() {
        return DEBIT;
    }
}
