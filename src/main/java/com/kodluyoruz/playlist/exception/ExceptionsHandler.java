package com.kodluyoruz.playlist.exception;

import com.couchbase.client.core.error.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(DocumentNotFoundException exception) {
        ExceptionDetail details = new ExceptionDetail(exception.getMessage(), new Date());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

}
