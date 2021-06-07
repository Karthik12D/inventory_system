package com.test.inventory.exception;

import com.test.inventory.domain.Error;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Exception handler for CSP validation
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class InventoryExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Method to handle validation failure exceptions which returns 500 server error
     * @return response entity with internal server error status code
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException( Exception e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDate(new Date());
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method to handle validation failure exceptions which returns 400 bad request
     * @return response entity with internal server error status code
     */
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setDate(new Date());
        error.setCode(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Method to handle resource not found exception which returns 404
     * @param ex the resource not found exception
     * @return response entity with exception message and status code
     */
    @ExceptionHandler(value = ResourceNotFoundExcpetion.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundExcpetion ex) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setDate(new Date());
        error.setCode(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
