package com.example.demo.controller.empleado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmpleadoNotFoundExceptionController {
    @ExceptionHandler(value = EmpleadoNotfoundException.class)
    public ResponseEntity<Object> exception(EmpleadoNotfoundException exception) {
        return new ResponseEntity<>("El empleado no existe", HttpStatus.NOT_FOUND);
    }
}
