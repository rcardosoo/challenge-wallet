package br.com.rcardoso.challenge.wallet.core.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoBalanceAvailableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoBalanceAvailableException() {
        super();
    }

    public NoBalanceAvailableException(String message) {
        super(message);
    }
}

