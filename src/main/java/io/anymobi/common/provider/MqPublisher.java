package io.anymobi.common.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MqPublisher {

    /*final RabbitTemplate rabbitTemplate;

    @Autowired
    public MqPublisher(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    public void websockMessagePublish(MessagePacket messagePacket) {
        rabbitTemplate.convertAndSend("exchange", "websock_message", messagePacket);
    }*/
}
