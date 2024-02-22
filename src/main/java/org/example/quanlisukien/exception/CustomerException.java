package org.example.quanlisukien.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomerException {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse notFoundException(NotFoundException ex , WebRequest request) {
    return new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(InternalServerException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse internalServerException(InternalServerException ex , WebRequest request) {
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),LocalDateTime.now());
  }
}
