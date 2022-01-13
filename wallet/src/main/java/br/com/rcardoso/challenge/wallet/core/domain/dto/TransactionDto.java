package br.com.rcardoso.challenge.wallet.core.domain.dto;

import br.com.rcardoso.challenge.wallet.core.domain.Transaction;
import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    @NotNull
    private Long requesterAccount;

    private Long destinationAccount;

    private LocalDateTime createdAt;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    @DecimalMin("0.01")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal value;

    public TransactionDto(Transaction entity) {
        this.setId(entity.getId());
        this.setTransactionType(entity.getTransactionType());
        this.setRequesterAccount(nonNull(entity.getSourceAccount())
                ? entity.getSourceAccount().getId()
                : null);
        this.setDestinationAccount(nonNull(entity.getDestinationAccount())
                ? entity.getDestinationAccount().getId()
                : null);
        this.setCreatedAt(entity.getCreatedAt());
        this.setValue(entity.getOriginalValue());
    }
}
