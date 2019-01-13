package io.anymobi.common.batch;

import io.anymobi.services.jpa.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchExecutor {

    private final Environment environment;
    private final UserService userService;

    @Autowired
    public BatchExecutor(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    //@Scheduled(cron="0 0/1 * * * ?")
    private synchronized void generateChartData() {
        String key = environment.getActiveProfiles()[0] + "_generateChartData";
        log.info("### generateChartData ready {} ### ", key);
        log.info("### generateChartData begin ###");

        userService.socketService();

        log.info("### generateChartData end ###");
    }

}
