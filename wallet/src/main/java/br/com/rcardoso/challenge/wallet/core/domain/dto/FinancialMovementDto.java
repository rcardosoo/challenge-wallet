package br.com.rcardoso.challenge.wallet.core.domain.dto;

import br.com.rcardoso.challenge.wallet.core.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialMovementDto {

    private Long id;

    private Long transactionId;

    private BigDecimal value;

    private String description;

    private MovementType movementType;

}
