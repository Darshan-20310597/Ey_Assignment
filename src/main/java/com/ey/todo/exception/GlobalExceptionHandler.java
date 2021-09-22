package com.ey.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ey.todo.entity.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(InputFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponse handleNumberFormatError(InputFormatException ex) {
        return new ErrorResponse(ex.getError(), ex.getDescription(), ex.getPath());
    }

    @ExceptionHandler(NoRecordsFetchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponse handleChassisEntityNotFoundException(NoRecordsFetchedException ex) {
        return new ErrorResponse(ex.getError(), ex.getDescription(), ex.getPath());
    }

}
