package com.altran.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void globalExceptionHandler_validationException () throws Exception {
        ValidationException validationException = new ValidationException("cod","message");
        ResponseEntity<ResponseError> response = globalExceptionHandler.validationExceptionHandler(validationException);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("cod", response.getBody().getCode());
        assertEquals("message", response.getBody().getMessage());

    }

}
