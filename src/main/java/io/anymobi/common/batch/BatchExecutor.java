package io.anymobi.common.batch;

import io.anymobi.common.provider.MqPublisher;
import io.anymobi.domain.dto.hr.MessagePacketDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchExecutor {

    private final Environment environment;
    private final MqPublisher mqPublisher;

    @Autowired
    public BatchExecutor(Environment environment, MqPublisher mqPublisher) {
        this.environment = environment;
        this.mqPublisher = mqPublisher;
    }

    @Scheduled(cron="0 0/1 * * * ?")
    private synchronized void generateChartData() {
        String key = environment.getActiveProfiles()[0] + "_generateChartData";
        log.info("### generateChartData ready {} ### ", key);
        log.info("### generateChartData begin ###");

        MessagePacketDto messagePacketDto = MessagePacketDto.builder()
                .userId("anymobi")
                .data("Hello RabbitMQ")
                .build();
        mqPublisher.websockMessagePublish(messagePacketDto);
        
        log.info("### generateChartData end ###");
    }

}
