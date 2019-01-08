package io.anymobi.common.advice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import io.anymobi.domain.dto.CurrentUserDTO;
import io.anymobi.domain.entity.User;
import io.anymobi.repositories.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(basePackages = "io.amymobi.controller")
@Order(1)
public class CurrentUserControllerAdvice {

    final UserRepository userRepository;
    final Environment environment;

    @Autowired
    public CurrentUserControllerAdvice(UserRepository userRepository, Environment environment) {
        this.userRepository = userRepository;
        this.environment = environment;
    }
    
    @ModelAttribute("user")
    public User getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : ((CurrentUserDTO) authentication.getPrincipal()).getUser();
    }

    @ModelAttribute("version")
    public long getVersion() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        return timestamp.getTime();
    }

    @ModelAttribute("baseurl")
    public String getBaseUrl() {
        return environment.getProperty("base.url");
    }

}
