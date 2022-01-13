package br.com.rcardoso.challenge.wallet.core.domain.dto;

import br.com.rcardoso.challenge.wallet.core.config.MapperConfig;
import br.com.rcardoso.challenge.wallet.core.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    @NotBlank
    private Long userId;

    private BigDecimal balance = BigDecimal.ZERO;

    public static AccountDto toDto(Account entity) {
        return MapperConfig.convert(entity, AccountDto.class);
    }

    public Account toEntity() {
        return MapperConfig.convert(this, Account.class);
    }

}
