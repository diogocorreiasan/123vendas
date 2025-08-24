package com.umdoistresvendas.salesapi.exception;

public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException(String message) {
        super(message);
    }
}
