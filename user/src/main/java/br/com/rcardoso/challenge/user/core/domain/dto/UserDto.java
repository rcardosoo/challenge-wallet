package br.com.rcardoso.challenge.user.core.domain.dto;

import br.com.rcardoso.challenge.user.core.config.MapperConfig;
import br.com.rcardoso.challenge.user.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    @Size(min=3, max=80)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public User toEntity() {
        return MapperConfig.convert(this, User.class);
    }

    public static List<UserDto> toDtoList(List<User> entities) {
        List<UserDto> dtoList = new ArrayList<>();
        entities.forEach(e -> dtoList.add(UserDto.toDto(e)));
        return dtoList;
    }

    public static UserDto toDto(User entity) {
        return MapperConfig.convert(entity, UserDto.class);
    }


}
