package br.com.rcardoso.challenge.wallet.core.services.factory;

import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;
import br.com.rcardoso.challenge.wallet.core.services.IFinancialTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FinancialTransactionServiceFactory {

    private Map<TransactionType, IFinancialTransactionService> financialTransactionOperations;

    @Autowired
    public FinancialTransactionServiceFactory(List<IFinancialTransactionService> transactionServiceList) {
        financialTransactionOperations = new HashMap<TransactionType, IFinancialTransactionService>();

        transactionServiceList.forEach(
                strategy -> financialTransactionOperations
                        .put(strategy.getType(), strategy));
    }

    public IFinancialTransactionService getFinancialTransactionService(TransactionType type) {
        return financialTransactionOperations.get(type);
    }

}
