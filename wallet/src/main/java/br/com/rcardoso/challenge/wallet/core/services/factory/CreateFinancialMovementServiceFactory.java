package br.com.rcardoso.challenge.wallet.core.services.factory;

import br.com.rcardoso.challenge.wallet.core.enums.MovementType;
import br.com.rcardoso.challenge.wallet.core.services.ICreateFinancialMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreateFinancialMovementServiceFactory {

    private Map<MovementType, ICreateFinancialMovementService> createFinancialMovementServices;

    @Autowired
    public CreateFinancialMovementServiceFactory(List<ICreateFinancialMovementService> createFinancialMovementServiceList) {
        createFinancialMovementServices = new HashMap<MovementType, ICreateFinancialMovementService>();

        createFinancialMovementServiceList.forEach(
                strategy -> createFinancialMovementServices
                        .put(strategy.getType(), strategy));
    }

    public ICreateFinancialMovementService getFinancialMovementService(MovementType type) {
        return createFinancialMovementServices.get(type);
    }

}
