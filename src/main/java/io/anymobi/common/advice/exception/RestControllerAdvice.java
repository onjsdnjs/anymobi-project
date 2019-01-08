package io.anymobi.common.advice.exception;

import io.anymobi.common.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class RestControllerAdvice{

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected String handleConflict(RuntimeException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        em.setMessage(ex.getMessage());

        return "forward:/handling";
    }

}
