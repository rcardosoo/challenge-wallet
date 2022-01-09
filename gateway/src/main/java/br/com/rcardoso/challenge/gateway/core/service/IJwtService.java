package br.com.rcardoso.challenge.gateway.core.service;

import br.com.rcardoso.challenge.gateway.core.exceptions.JwtTokenMalformedException;
import br.com.rcardoso.challenge.gateway.core.exceptions.JwtTokenMissingException;
import io.jsonwebtoken.Claims;

public interface IJwtService {

    Claims getClaims(final String token);

    void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException;
}
