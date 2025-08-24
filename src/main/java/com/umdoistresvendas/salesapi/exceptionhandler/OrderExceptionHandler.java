package com.umdoistresvendas.salesapi.exceptionhandler;

import com.umdoistresvendas.salesapi.exception.DuplicateOrderException;
import com.umdoistresvendas.salesapi.exception.QuantityItemsExceededException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> responseException(final DuplicateOrderException ex){

        var response = ErrorResponse
                .builder()
                .detail(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(QuantityItemsExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> quantityItemException(final QuantityItemsExceededException ex){

        var response = ErrorResponse
                .builder()
                .detail(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

}
