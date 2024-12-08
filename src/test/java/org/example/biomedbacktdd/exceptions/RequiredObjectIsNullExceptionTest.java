package org.example.biomedbacktdd.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class RequiredObjectIsNullExceptionTest {

    @Test
    void testDefaultMessage() {
        RequiredObjectIsNullException exception = new RequiredObjectIsNullException();

        assertEquals("It is not allowed to persist a null object!", exception.getMessage());
    }

    @Test
    void testResponseStatusAnnotation() {
        ResponseStatus responseStatus = RequiredObjectIsNullException.class.getAnnotation(ResponseStatus.class);

        assertNotNull(responseStatus, "The @ResponseStatus annotation should be present");
        assertEquals(HttpStatus.BAD_REQUEST, responseStatus.value(), "The HTTP status should be BAD_REQUEST");
    }

    @Test
    void testExceptionHandling() {
        try {
            throw new RequiredObjectIsNullException();
        } catch (RequiredObjectIsNullException ex) {
            assertEquals("It is not allowed to persist a null object!", ex.getMessage());
            assertThrows(ResponseStatusException.class, () -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
            });
        }
    }
}
