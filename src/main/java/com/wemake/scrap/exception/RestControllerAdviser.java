package com.wemake.scrap.exception;

import com.wemake.scrap.common.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.io.IOException;
import java.net.UnknownHostException;


@Slf4j
@RestControllerAdvice
public class RestControllerAdviser {

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidErrors(UnexpectedTypeException exception) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorMessage> validationException(BindException bindException) {
        BindingResult bindingResult = bindException.getBindingResult();
        StringBuilder message = new StringBuilder();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            message.append(fieldError.getField());
            message.append(" : ");
            message.append(fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST, message.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidErrors() {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "URL이 올바르지 않습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CrawlerEmptyException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidErrors(CrawlerEmptyException exception) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
