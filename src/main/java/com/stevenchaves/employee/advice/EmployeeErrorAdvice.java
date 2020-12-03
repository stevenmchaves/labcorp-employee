package com.stevenchaves.employee.advice;

import com.stevenchaves.employee.exceptions.ItemNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
class EmployeeErrorAdvice  extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(ItemNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String itemNotFoundHandler(ItemNotFoundException ex) {
    return ex.getMessage();
  }

}
