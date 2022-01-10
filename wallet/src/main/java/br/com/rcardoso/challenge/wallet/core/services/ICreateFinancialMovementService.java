package br.com.rcardoso.challenge.wallet.core.services;

import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.domain.dto.FinancialMovementDto;
import br.com.rcardoso.challenge.wallet.core.enums.MovementType;

public interface ICreateFinancialMovementService {

    void create(FinancialMovementDto financialMovementDto, Transaction transaction);

    MovementType getType();
}
