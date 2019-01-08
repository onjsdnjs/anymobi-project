package io.anymobi.common.handler.websocket;

import io.anymobi.domain.dto.MessagePacket;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebsockMsgBrocker {

    private SimpMessageSendingOperations simpMessagingTemplate;

   /* @Autowired
    public WebsockMsgBrocker(SimpMessageSendingOperations messaging){
        this.simpMessagingTemplate=messaging;
    }*/

    @Data
    @Builder
    public static class SendToDTO {
        private String cmd;
        private Long id;
        private Object data;
    }

    public void send(MessagePacket messagePacket) {
        log.info(" ======= {}", messagePacket);
        try {
            simpMessagingTemplate.convertAndSend("/topic/exchange", messagePacket);
        } catch (Exception ex) {

        }
    }
}
