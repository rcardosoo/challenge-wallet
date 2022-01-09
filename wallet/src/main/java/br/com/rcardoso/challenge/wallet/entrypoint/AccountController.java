package br.com.rcardoso.challenge.wallet.entrypoint;

import br.com.rcardoso.challenge.wallet.core.domain.dto.AccountDto;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.read(id));
    }

}
