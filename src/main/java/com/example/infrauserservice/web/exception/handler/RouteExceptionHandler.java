package com.example.infrauserservice.web.exception.handler;

import com.example.infrauserservice.web.exception.model.EntityNotFoundException;
import com.example.infrauserservice.web.exception.model.ExceptionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestControllerAdvice
public class RouteExceptionHandler {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {

        ExceptionResponse response = new ExceptionResponse(
                dateFormat.format(new Date()),
                ExceptionStatus.US001,
                e.getMessage());

        log.error(response.getDetail());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String detail = e.getMessage();

        ExceptionResponse response = new ExceptionResponse(
                dateFormat.format(new Date()),
                ExceptionStatus.US002,
                detail);

        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String detail = e.getFieldErrors().stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .toList().toString();

        ExceptionResponse response = new ExceptionResponse(
                dateFormat.format(new Date()),
                ExceptionStatus.US002,
                detail
        );

        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
