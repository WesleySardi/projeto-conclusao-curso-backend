package org.example.biomedbacktdd.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String testMessage = "Resource not found for ID: 123";
        ResourceNotFoundException exception = new ResourceNotFoundException(testMessage);

        assertEquals(testMessage, exception.getMessage(), "The exception message should match the provided message.");
    }

    @Test
    void testResponseStatusAnnotation() {
        ResponseStatus responseStatus = ResourceNotFoundException.class.getAnnotation(ResponseStatus.class);

        assertNotNull(responseStatus, "The @ResponseStatus annotation should be present.");
        assertEquals(HttpStatus.NOT_FOUND, responseStatus.value(), "The HTTP status should be NOT_FOUND.");
    }

    @Test
    void testExceptionHandling() {
        String testMessage = "Resource not found!";
        try {
            throw new ResourceNotFoundException(testMessage);
        } catch (ResourceNotFoundException ex) {
            assertEquals(testMessage, ex.getMessage(), "The exception message should match the provided message.");
            assertThrows(ResponseStatusException.class, () -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
            });
        }
    }
}
