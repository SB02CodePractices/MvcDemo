package com.sb02.mvcdemo.exception.user;

import com.sb02.mvcdemo.exception.ResponseErrorBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseErrorBody> handleUserNotFound(UserNotFound e) {
        logger.error("{} handled by UserExceptionAdvice", e.getMessage());
        e.printStackTrace();

        return ResponseEntity.badRequest()
                .body(new ResponseErrorBody(e.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseErrorBody> handleUserException(UserException e) {
        return ResponseEntity.badRequest().body(new ResponseErrorBody(e.getMessage()));
    }
}
