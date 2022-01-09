package br.com.rcardoso.challenge.user.entrypoint;

import br.com.rcardoso.challenge.user.core.domain.dto.AccessTokenDto;
import br.com.rcardoso.challenge.user.core.domain.dto.CredentialsDto;
import br.com.rcardoso.challenge.user.core.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping()
    public ResponseEntity<AccessTokenDto> auth(@Valid @RequestBody CredentialsDto credentials) {
        return ResponseEntity.ok(authService.authenticate(credentials));
    }

}
