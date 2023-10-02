package org.spring.hackathon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
                    //<?>는 어느 타입이든 들어갈 수 있다는 의미
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(e.getMessage());

  }

  //로그인 Exception
  @ExceptionHandler(AppException.class)
  public ResponseEntity<?> appExceptionHandler(AppException e) {

    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
        .body(e.getErrorCode().name() + " " + e.getMessage());

  }

}
