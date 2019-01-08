/*
package io.anymobi.common.advice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SqlControllerAdvice {

    @ExceptionHandler(SQLException.class)
    public String sqlException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        request.setAttribute("msg", e.toString());
        log.info(e.toString());
        return "/error";
    }

}
*/
