package br.com.cooperative.assembly.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.exception.ErrorResponse;
import br.com.cooperative.assembly.exception.FeignExceptionException;
import br.com.cooperative.assembly.exception.InvalidDocumentException;
import br.com.cooperative.assembly.exception.ProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ErrorHandlerControllerTest {

    private ErrorHandlerController errorHandlerController;

    @Before
    public void setUp() {
        errorHandlerController = new ErrorHandlerController();
    }

    @Test
    public void testBusinessException() {
        BusinessException businessException = new BusinessException("Err");

        ResponseEntity<ErrorResponse> response = errorHandlerController.handleBusinessException(businessException);

        Assert.assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());
    }

    @Test
    public void testBadRequestException() {
        InvalidDocumentException invalidDocumentException = new InvalidDocumentException("Err");

        ResponseEntity<ErrorResponse> response = errorHandlerController.handleInvalidDocumentException(invalidDocumentException);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testFeignExceptionException() {
        FeignExceptionException feignExceptionException = new FeignExceptionException("Err");

        ResponseEntity<ErrorResponse> response = errorHandlerController.handleFeignExceptionException(feignExceptionException);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testProcessingException() {
        ProcessingException processingException = new ProcessingException("");

        ResponseEntity<ErrorResponse> response = errorHandlerController.handleProcessingException(processingException);

        Assert.assertEquals(HttpStatus.PROCESSING, response.getStatusCode());
    }
}