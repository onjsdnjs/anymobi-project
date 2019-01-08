package io.anymobi.common.advice.exception;

import io.anymobi.common.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class WebControllerAdvice{


    @ExceptionHandler(SQLException.class)
    public String sqlException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        request.setAttribute("msg", e.toString());
        log.info(e.toString());
        return "forward:/handling";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleConflict(Exception e) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        em.setMessage(e.getMessage());

        return "forward:/handling";
    }

}
