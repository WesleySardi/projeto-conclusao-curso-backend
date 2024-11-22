package org.example.biomedbacktdd.controller.sms;

import org.example.biomedbacktdd.dto.commands.NewSmsCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.controllers.sms.SmsHandlerController;
import org.example.biomedbacktdd.handlers.sms.SmsHandlerHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SmsHandlerControllerTest {

    @Mock
    private SmsHandlerHandler handler;

    @InjectMocks
    private SmsHandlerController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        StatusResponseViewModel<String> mockResponseContent = new StatusResponseViewModel<>(
                "All records fetched",
                "Info message",
                "Success",
                200,
                true
        );

        when(handler.handleFindAll(PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "smsCode"))))
                .thenReturn(ResponseEntity.ok(mockResponseContent));

        var response = controller.findAll(0, 12, "asc");

        assertNotNull(response);

        verify(handler, times(1)).handleFindAll(PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "smsCode")));
    }

    @Test
    void testFindById() {
        when(handler.handleFindById(1)).thenReturn(ResponseEntity.ok().build());

        var response = controller.findById(1);

        assertNotNull(response);
        verify(handler, times(1)).handleFindById(1);
    }

    @Test
    void testCreate() {
        NewSmsCommand command = new NewSmsCommand(1234, Timestamp.valueOf("2024-11-17 10:00:00"),
                Timestamp.valueOf("2024-11-17 12:00:00"), "1234567890", "12345678901");

        StatusResponseViewModel<String> mockResponse = new StatusResponseViewModel<>(
                "Message created successfully",
                "Info message",
                "Success",
                200,
                true
        );
        when(handler.handleCreate(command)).thenReturn(ResponseEntity.ok(mockResponse));

        var response = controller.create(command);

        assertNotNull(response);
        verify(handler, times(1)).handleCreate(command);
    }


    @Test
    void testDelete() {
        when(handler.handleDelete(1)).thenReturn(ResponseEntity.ok().build());

        var response = controller.delete(1);

        assertNotNull(response);
        verify(handler, times(1)).handleDelete(1);
    }

    @Test
    void testVerifySmsCode() {
        when(handler.handleVerifySmsCode(1234, Timestamp.valueOf("2024-11-17 10:00:00"), "12345678901")).thenReturn(ResponseEntity.ok().build());

        var response = controller.verifySmsCode(1234, Timestamp.valueOf("2024-11-17 10:00:00"), "12345678901");

        assertNotNull(response);
        verify(handler, times(1)).handleVerifySmsCode(1234, Timestamp.valueOf("2024-11-17 10:00:00"), "12345678901");
    }
}
