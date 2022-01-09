package br.com.rcardoso.challenge.user.core.service;

import br.com.rcardoso.challenge.user.core.domain.User;
import br.com.rcardoso.challenge.user.core.domain.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    UserDto create(UserDto userDTO);

    UserDto read(Long id);

    User getByEmail(String email);

    Page<UserDto> list(Pageable pageable);

    UserDto update(Long id, UserDto userDTO);

    UserDto delete(Long id);

}
