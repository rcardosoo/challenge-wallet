package br.com.rcardoso.challenge.wallet.core.services.impl;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import br.com.rcardoso.challenge.wallet.core.domain.dto.AccountDto;
import br.com.rcardoso.challenge.wallet.core.exceptions.NotFoundException;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import br.com.rcardoso.challenge.wallet.dataprovider.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;

    @Override
    public AccountDto create(Long userId) {
        Account account = Account.builder()
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .build();

        return AccountDto.toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto read(Long id) {
        return accountRepository.findById(id)
                .map(AccountDto::toDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public BigDecimal getBalance(Long accountId) {
        return accountRepository.findBalanceById(accountId);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }
}
