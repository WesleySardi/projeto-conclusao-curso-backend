package org.example.biomedbacktdd.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionTest {

    @Test
    void testExceptionMessage() {
        String testMessage = "Service operation failed!";
        ServiceException exception = new ServiceException(testMessage);

        assertEquals(testMessage, exception.getMessage(), "The exception message should match the provided message.");
    }

    @Test
    void testExceptionInheritance() {
        ServiceException exception = new ServiceException("Test message");

        assertTrue(exception instanceof RuntimeException, "ServiceException should inherit from RuntimeException.");
    }

    @Test
    void testThrowingServiceException() {
        String testMessage = "An error occurred in the service.";
        Exception thrownException = assertThrows(ServiceException.class, () -> {
            throw new ServiceException(testMessage);
        });

        assertEquals(testMessage, thrownException.getMessage(), "Thrown exception message should match the provided message.");
    }
}
