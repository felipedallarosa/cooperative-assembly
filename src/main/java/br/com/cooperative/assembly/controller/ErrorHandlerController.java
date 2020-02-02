package br.com.cooperative.assembly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.exception.ErrorMessage;
import br.com.cooperative.assembly.exception.ErrorResponse;
import br.com.cooperative.assembly.exception.FeignExceptionException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException businessException){
        log.error(ErrorMessage.ERROR, businessException);
        ErrorResponse errorResponse = businessException.getErrorResponse();
        return new ResponseEntity<>(errorResponse, businessException.getType().getHttpStatus());
    }

    @ExceptionHandler(FeignExceptionException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestException(final FeignExceptionException feignExceptionException){
        log.error(ErrorMessage.ERROR, feignExceptionException);
        ErrorResponse errorResponse = feignExceptionException.getErrorResponse();
        return new ResponseEntity<>(errorResponse, feignExceptionException.getType().getHttpStatus());
    }

}
