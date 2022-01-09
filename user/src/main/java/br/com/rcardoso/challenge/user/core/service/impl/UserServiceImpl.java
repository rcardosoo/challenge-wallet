package br.com.rcardoso.challenge.user.core.service.impl;

import br.com.rcardoso.challenge.user.core.domain.User;
import br.com.rcardoso.challenge.user.core.domain.dto.UserDto;
import br.com.rcardoso.challenge.user.core.exceptions.ConflictException;
import br.com.rcardoso.challenge.user.core.exceptions.NotFoundException;
import br.com.rcardoso.challenge.user.core.service.IUserDomainEventService;
import br.com.rcardoso.challenge.user.core.service.IUserService;
import br.com.rcardoso.challenge.user.dataprovider.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.rcardoso.challenge.user.core.domain.dto.UserDto.toDto;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final IUserDomainEventService userDomainEventService;

    @Override
    public UserDto create(UserDto userDTO) {
        try {
            User user = userDTO.toEntity();
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            final var savedUser = toDto(userRepository.save(user));
            userDomainEventService.produceUserDomainEvent(savedUser);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    @Override
    public UserDto read(Long id) {
        return userRepository.findById(id)
                .map(UserDto::toDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<UserDto> list(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        final List<UserDto> list = UserDto.toDtoList(page.getContent());
        return new PageImpl<>(list, pageable, page.getTotalPages());
    }

    @Override
    public UserDto update(Long id, UserDto userDTO) {
        return userRepository.findById(id).map(user -> {
            try {
                user.setName(nonNull(userDTO.getName()) ? userDTO.getName() : user.getName());
                user.setEmail(nonNull(userDTO.getEmail()) ? userDTO.getEmail() : user.getEmail());
                user.setPassword(nonNull(userDTO.getPassword())
                        ? passwordEncoder.encode(userDTO.getName())
                        : user.getPassword());

                return UserDto.toDto(userRepository.save(user));
            } catch (DataIntegrityViolationException e) {
                throw new ConflictException(e.getMessage());
            }
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserDto delete(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.deleteById(id);
            return UserDto.toDto(user);
        }).orElseThrow(NotFoundException::new);
    }

}
