package br.com.cooperative.assembly.domain;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorType {

    BUSINESS(HttpStatus.PRECONDITION_FAILED),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    VALIDATION(HttpStatus.BAD_REQUEST),
    PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED);

    private final HttpStatus httpStatus;
}