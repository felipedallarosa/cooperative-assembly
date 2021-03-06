package br.com.cooperative.assembly.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.exception.ErrorResponse;
import br.com.cooperative.assembly.exception.FeignExceptionException;
import br.com.cooperative.assembly.exception.InvalidDocumentException;
import br.com.cooperative.assembly.exception.ProcessingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException businessException){
        ErrorResponse errorResponse = businessException.getErrorResponse();
        return new ResponseEntity<>(errorResponse, businessException.getType().getHttpStatus());
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidDocumentException(final InvalidDocumentException invalidDocumentException){
        ErrorResponse errorResponse = invalidDocumentException.getErrorResponse();
        return new ResponseEntity<>(errorResponse, invalidDocumentException.getType().getHttpStatus());
    }

    @ExceptionHandler(FeignExceptionException.class)
    public final ResponseEntity<ErrorResponse> handleFeignExceptionException(final FeignExceptionException feignExceptionException){
        ErrorResponse errorResponse = feignExceptionException.getErrorResponse();
        return new ResponseEntity<>(errorResponse, feignExceptionException.getType().getHttpStatus());
    }


    @ExceptionHandler(ProcessingException.class)
    public final ResponseEntity<ErrorResponse> handleProcessingException(final ProcessingException processingException){
        ErrorResponse errorResponse = processingException.getErrorResponse();
        return new ResponseEntity<>(errorResponse, processingException.getType().getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                            HttpHeaders headers,
                                                            HttpStatus status,
                                                            WebRequest request) {
        List<String> erros = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            erros.add(errorMessage + " " + fieldName);
        });
        ErrorResponse errorResponse = new ErrorResponse("ERROR", erros, LocalDateTime.now());

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
