package io.anymobi.config.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(value = { @ComponentScan("io.anymobi.repositories"), @ComponentScan("io.anymobi.services") })
public class RootConfig { }
