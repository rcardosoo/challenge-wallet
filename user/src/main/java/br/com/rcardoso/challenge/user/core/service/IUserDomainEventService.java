package br.com.rcardoso.challenge.user.core.service;

import br.com.rcardoso.challenge.user.core.domain.dto.UserDto;

public interface IUserDomainEventService {

    void produceUserDomainEvent(UserDto userDto);
}
