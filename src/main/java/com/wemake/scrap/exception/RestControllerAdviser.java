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
            message.append(". 입력 [ ");
            message.append(fieldError.getRejectedValue());
            message.append(" ] ");
        });
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST, message.toString()), HttpStatus.BAD_REQUEST);
    }
}
