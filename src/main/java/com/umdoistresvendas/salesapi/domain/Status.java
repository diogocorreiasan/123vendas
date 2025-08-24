package com.umdoistresvendas.salesapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    CREATED,
    CANCELLED,
    SUCCESS,
    ERROR
}
