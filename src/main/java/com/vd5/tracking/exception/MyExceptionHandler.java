package com.vd5.tracking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResource handleNoSuchElement(NoSuchElementException ex) {
        return new ErrorResource(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource handleIllegalState(IllegalStateException ex) {
        return new ErrorResource(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource handleValidationState(ValidationException ex) {
        List<FieldErrorResource> resourceList = ex.getErrorFields().stream().map(x -> fieldErrorResourceConverter(x)).collect(Collectors.toList());
        return new ErrorResource(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), resourceList);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResource handleConflict(ConflictException ex) {
        return new ErrorResource(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResource handleBusinessLogicException(BusinessLogicException ex) {
        return new ErrorResource(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResource handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ErrorResource(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    private static FieldErrorResource fieldErrorResourceConverter(FieldError fieldError) {
        return new FieldErrorResource(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());
    }

}
