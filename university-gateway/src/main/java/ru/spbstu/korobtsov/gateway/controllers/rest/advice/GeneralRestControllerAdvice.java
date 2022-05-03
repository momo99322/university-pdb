package ru.spbstu.korobtsov.gateway.controllers.rest.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.spbstu.korobtsov.api.exceptions.BasicNotFoundException;
import ru.spbstu.korobtsov.api.exceptions.dto.ErrorEntity;

@Slf4j
@RestControllerAdvice
public class GeneralRestControllerAdvice {
    @ExceptionHandler(BasicNotFoundException.class)

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorEntity handleNotFoundExceptions(BasicNotFoundException notFoundException) {
        log.error("Thrown ", notFoundException);
        return new ErrorEntity(HttpStatus.NOT_FOUND.getReasonPhrase(), notFoundException.getMessage());
    }
}
