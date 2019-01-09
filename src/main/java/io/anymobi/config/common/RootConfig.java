package io.anymobi.config.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScans(value = { @ComponentScan("io.anymobi.repositories"),
                          @ComponentScan("io.anymobi.services"),
                          @ComponentScan("io.anymobi.common") })
public class RootConfig { }
