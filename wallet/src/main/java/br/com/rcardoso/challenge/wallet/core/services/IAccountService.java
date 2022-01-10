package br.com.rcardoso.challenge.wallet.core.services;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import br.com.rcardoso.challenge.wallet.core.domain.dto.AccountDto;

import java.math.BigDecimal;

public interface IAccountService {

    AccountDto create(Long userId);

    AccountDto read(Long id);

    BigDecimal getBalance(Long accountId);

    void updateAccount(Account account);
}
