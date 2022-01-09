package br.com.rcardoso.challenge.wallet.core.services;

import br.com.rcardoso.challenge.wallet.core.domain.dto.AccountDto;

public interface IAccountService {

    AccountDto create(Long userId);

    AccountDto read(Long id);
}
