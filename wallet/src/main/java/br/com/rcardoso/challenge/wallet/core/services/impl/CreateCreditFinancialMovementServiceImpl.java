package br.com.rcardoso.challenge.wallet.core.services.impl;

import br.com.rcardoso.challenge.wallet.core.domain.FinancialMovement;
import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.domain.dto.FinancialMovementDto;
import br.com.rcardoso.challenge.wallet.core.enums.MovementType;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import br.com.rcardoso.challenge.wallet.core.services.ICreateFinancialMovementService;
import br.com.rcardoso.challenge.wallet.dataprovider.IFinancialMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.rcardoso.challenge.wallet.core.enums.MovementType.CREDIT;

@Service
@RequiredArgsConstructor
public class CreateCreditFinancialMovementServiceImpl implements ICreateFinancialMovementService {

    private final IFinancialMovementRepository financialMovementRepository;

    private final IAccountService accountService;

    @Override
    public void create(FinancialMovementDto financialMovementDto, Transaction transaction) {

        var account = transaction.getDestinationAccount();
        account.setBalance(account.getBalance().add(financialMovementDto.getValue()));
        accountService.updateAccount(account);

        financialMovementRepository.save(FinancialMovement.builder()
                .description(financialMovementDto.getDescription())
                .movementType(CREDIT)
                .transaction(transaction)
                .value(financialMovementDto.getValue())
                .build());
    }

    @Override
    public MovementType getType() {
        return CREDIT;
    }
}
