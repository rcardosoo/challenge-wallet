package br.com.rcardoso.challenge.user.core.service;

import br.com.rcardoso.challenge.user.core.exceptions.JwtTokenMalformedException;
import br.com.rcardoso.challenge.user.core.exceptions.JwtTokenMissingException;
import io.jsonwebtoken.Claims;

public interface IJwtService {

    Claims getClaims(final String token);

    String generateToken(String id);

    void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException;
}
