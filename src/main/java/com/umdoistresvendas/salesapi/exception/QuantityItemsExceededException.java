package com.umdoistresvendas.salesapi.exception;

public class QuantityItemsExceededException extends RuntimeException {
    public QuantityItemsExceededException(String message) {
        super(message);
    }
}
