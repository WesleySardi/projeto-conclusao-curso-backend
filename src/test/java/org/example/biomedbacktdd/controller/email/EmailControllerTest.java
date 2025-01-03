package org.example.biomedbacktdd.controller.email;

import org.example.biomedbacktdd.dto.commands.NewEmailCommand;
import org.example.biomedbacktdd.dto.results.NewEmailResult;
import org.example.biomedbacktdd.dto.viewmodels.NewEmailViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.controllers.email.EmailController;
import org.example.biomedbacktdd.handlers.email.EmailHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailControllerTest {

    @Mock
    private EmailHandler emailHandler;

    @InjectMocks
    private EmailController emailController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllEmails() {
        StatusResponseViewModel<PagedModel<EntityModel<NewEmailViewModel>>> mockResponse = new StatusResponseViewModel<>(null, "Success", "Success", 200, true);
        when(emailHandler.handleFindAll(any())).thenReturn(ResponseEntity.ok(mockResponse));

        ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewEmailViewModel>>>> responseEntity = emailController.findAll(0, 10, "asc");

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Success", responseEntity.getBody().getStatusMessage());
        assertTrue(responseEntity.getBody().getIsOk());

        verify(emailHandler, times(1)).handleFindAll(any());
    }

    @Test
    void testCreateEmail() {
        Timestamp sendDate = Timestamp.valueOf("2024-11-17 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2024-11-17 10:30:00");

        NewEmailCommand command = new NewEmailCommand(1234, sendDate, returnDate, "test@example.com", "12345678901");

        StatusResponseViewModel<NewEmailResult> mockResponse = new StatusResponseViewModel<>(null, "QR Code Sent", "Email created successfully", 200, true);

        when(emailHandler.handleCreate(command)).thenReturn(ResponseEntity.ok(mockResponse));

        ResponseEntity<StatusResponseViewModel<NewEmailResult>> responseEntity = emailController.create(command);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Email created successfully", responseEntity.getBody().getStatusMessage());
        assertTrue(responseEntity.getBody().getIsOk());

        verify(emailHandler, times(1)).handleCreate(command);
    }

    @Test
    void testVerifyEmailCode() {
        StatusResponseViewModel<Boolean> mockResponse = new StatusResponseViewModel<>(null, "Code Verified", "Code verified successfully", 200, true);
        when(emailHandler.handleVerifyEmailCode("test@example.com", 1234))
                .thenReturn(ResponseEntity.ok(mockResponse));

        ResponseEntity<StatusResponseViewModel<Boolean>> responseEntity = emailController.verifyEmailCode("test@example.com", 1234);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Code verified successfully", responseEntity.getBody().getStatusMessage());
        assertTrue(responseEntity.getBody().getIsOk());

        verify(emailHandler, times(1)).handleVerifyEmailCode("test@example.com", 1234);
    }

    @Test
    void testSendQrCode() {
        StatusResponseViewModel<String> mockResponse = new StatusResponseViewModel<>(null, "QR Code Sent", "QR code sent successfully", 200, true);
        when(emailHandler.handleSendQrCodeWithSendGrid("test@example.com"))
                .thenReturn(ResponseEntity.ok(mockResponse));

        ResponseEntity<StatusResponseViewModel<String>> responseEntity = emailController.sendQrCodeWithSendGrid("test@example.com");

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("QR code sent successfully", responseEntity.getBody().getStatusMessage());
        assertTrue(responseEntity.getBody().getIsOk());

        verify(emailHandler, times(1)).handleSendQrCodeWithSendGrid("test@example.com");
    }
}
