package io.anymobi.common.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/batch")
public class ChartGenerateBatchController {

    private final Environment environment;

    @Autowired
    public ChartGenerateBatchController(Environment environment) {
        this.environment = environment;
    }

    @Scheduled(cron="0 0/1 * * * ?")
    private synchronized void generateChartData() {
        String key = environment.getActiveProfiles()[0] + "_generateChartData";
        log.info("### generateChartData ready {} ### ", key);
        log.info("### generateChartData begin ###");
//            chartService.generateChart();
        log.info("### generateChartData end ###");
    }

}
