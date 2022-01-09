package br.com.rcardoso.challenge.user.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
