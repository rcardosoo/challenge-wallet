package br.com.rcardoso.challenge.user.core.service.impl;

import br.com.rcardoso.challenge.user.core.domain.dto.UserDto;
import br.com.rcardoso.challenge.user.core.service.IUserDomainEventService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainEventServiceImpl implements IUserDomainEventService {

    @Value("${wallet.kafka.topic.user-domain-event}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void produceUserDomainEvent(UserDto userDto) {
        kafkaTemplate.send(topic, new Gson().toJson(userDto));
    }
}
