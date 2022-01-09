package br.com.rcardoso.challenge.user.core.service;

import br.com.rcardoso.challenge.user.core.domain.dto.AccessTokenDto;
import br.com.rcardoso.challenge.user.core.domain.dto.CredentialsDto;

public interface IAuthService {

    AccessTokenDto authenticate(CredentialsDto credentials);
}
