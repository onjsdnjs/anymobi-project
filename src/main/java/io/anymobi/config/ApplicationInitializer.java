package io.anymobi.config;

import io.anymobi.config.common.AppConfig;
import io.anymobi.config.common.RootConfig;
import io.anymobi.config.data.*;
import io.anymobi.config.web.WebConfig;
import io.anymobi.config.web.WebSecurityConfig;
import io.anymobi.config.web.WebSocketConfig;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class, AppConfig.class, AmqpConfig.class, DataConfig.class,
                            JpaDataConfig.class, MybatisDataConfig.class, SessionConfig.class, WebSocketConfig.class, WebSecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "prod");
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {

        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter};
    }

}