package br.com.rcardoso.challenge.user.core.service.impl;

import br.com.rcardoso.challenge.user.core.domain.User;
import br.com.rcardoso.challenge.user.core.domain.dto.AccessTokenDto;
import br.com.rcardoso.challenge.user.core.domain.dto.CredentialsDto;
import br.com.rcardoso.challenge.user.core.exceptions.UnauthorizedException;
import br.com.rcardoso.challenge.user.core.service.IAuthService;
import br.com.rcardoso.challenge.user.core.service.IJwtService;
import br.com.rcardoso.challenge.user.core.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IJwtService jwtService;

    private final IUserService userService;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.token.validity}")
    private long tokenValidity;

    @Override
    @SneakyThrows
    public AccessTokenDto authenticate(CredentialsDto credentials) {
        User user = userService.getByEmail(credentials.getEmail());

        if (passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
            final var token = jwtService.generateToken(String.valueOf(user.getId()));

            return AccessTokenDto.builder()
                    .token(token)
                    .expTimeInSeconds(MILLISECONDS.toSeconds(tokenValidity))
                    .build();
        }

        throw new UnauthorizedException();
    }
}
