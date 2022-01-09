package br.com.rcardoso.challenge.wallet.entrypoint;

import br.com.rcardoso.challenge.wallet.core.domain.dto.UserDto;
import br.com.rcardoso.challenge.wallet.core.services.IAccountService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainEventConsumer {

    private final IAccountService accountService;

    @KafkaListener(topics = "${wallet.kafka.topic.user-domain-event}",
            groupId = "${spring.kafka.consumer.group-id")
    public void consume(ConsumerRecord<String, String> payload){
        UserDto user = new Gson().fromJson(payload.value(), UserDto.class);
        accountService.create(user.getId());
    }
}
