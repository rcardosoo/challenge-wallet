package br.com.rcardoso.challenge.user.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDto {

    private String token;

    private long expTimeInSeconds;
}
