package com.hackerrank.sample.exception;

import static com.hackerrank.sample.constant.Constant.GENERIC_ERROR_MESSAGE;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("No Record found for id " + ex.getMessage());
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> handleEmptyResultDataAccessException(
      EmptyResultDataAccessException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record does not exists for given id");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAnyException(Exception ex) {
    ResponseStatus findAnnotation =
        AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
    HttpStatus httpStatus =
        findAnnotation != null ? findAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    return ResponseEntity.status(httpStatus).body(GENERIC_ERROR_MESSAGE);
  }

}