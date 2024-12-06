package org.example.biomedbacktdd.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        globalExceptionHandler = new GlobalExceptionHandler();
        globalExceptionHandler.logger = mock(java.util.logging.Logger.class); // Simula o logger
    }

    @Test
    void testHandleResourceNotFound() {
        String errorMessage = "Resource not found!";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        ResponseEntity<String> response = globalExceptionHandler.handleResourceNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void testHandleValidationExceptions() {
        FieldError fieldError1 = new FieldError("object", "field1", "Field1 is invalid");
        FieldError fieldError2 = new FieldError("object", "field2", "Field2 is required");

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> errors = response.getBody();
        assertEquals(2, errors.size());
        assertEquals("Field1 is invalid", errors.get("field1"));
        assertEquals("Field2 is required", errors.get("field2"));
    }

    @Test
    void testHandleServiceException() {
        String errorMessage = "Service error occurred!";
        ServiceException exception = new ServiceException(errorMessage);

        ResponseEntity<String> response = globalExceptionHandler.handleServiceException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void testHandleAllExceptions() {
        Exception exception = new Exception("Unexpected error");

        ResponseEntity<String> response = globalExceptionHandler.handleAllExceptions(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.", response.getBody());
    }
}
