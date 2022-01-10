package br.com.rcardoso.challenge.wallet.core.domain.dto;

import br.com.rcardoso.challenge.wallet.core.config.MapperConfig;
import br.com.rcardoso.challenge.wallet.core.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @Getter
    private Long id;

    @Getter
    @Setter
    @NotBlank
    private Long userId;

    @Getter
    private BigDecimal balance = BigDecimal.ZERO;

    public static AccountDto toDto(Account entity) {
        return MapperConfig.convert(entity, AccountDto.class);
    }

}
