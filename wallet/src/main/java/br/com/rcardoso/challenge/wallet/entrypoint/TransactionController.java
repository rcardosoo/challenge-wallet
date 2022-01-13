package br.com.rcardoso.challenge.wallet.entrypoint;

import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;
import br.com.rcardoso.challenge.wallet.core.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping()
    public ResponseEntity<TransactionDto> create(@Valid @RequestBody TransactionDto transaction) {
        return ResponseEntity.ok(transactionService.create(transaction));
    }

}
