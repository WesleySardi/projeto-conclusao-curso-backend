package org.example.biomedbacktdd.handler.email;

import org.example.biomedbacktdd.DTO.commands.NewEmailCommand;
import org.example.biomedbacktdd.DTO.results.NewEmailResult;
import org.example.biomedbacktdd.DTO.viewmodels.NewEmailViewModel;
import org.example.biomedbacktdd.DTO.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.email.EmailHandler;
import org.example.biomedbacktdd.services.interfaces.email.IEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EmailHandlerTest {

    @Mock
    private IEmailService emailService;

    @InjectMocks
    private EmailHandler emailHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleCreate() {
        // Arrange
        Timestamp sendDate = Timestamp.valueOf("2024-11-17 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2024-11-17 10:30:00");
        NewEmailCommand command = new NewEmailCommand(1234, sendDate, returnDate, "test@example.com", "12345678901");

        NewEmailResult result = new NewEmailResult();

        when(emailService.create(command)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel> response = emailHandler.handleCreate(command);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Email criado com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).create(command);
    }

    @Test
    void testHandleVerifyEmailCode() {
        when(emailService.verifyEmailCode("test@example.com", 1234)).thenReturn(true);

        ResponseEntity<StatusResponseViewModel> response = emailHandler.handleVerifyEmailCode("test@example.com", 1234);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Código do email válido.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).verifyEmailCode("test@example.com", 1234);
    }

    @Test
    void testHandleFindAll() {
        NewEmailViewModel emailViewModel = new NewEmailViewModel();
        EntityModel<NewEmailViewModel> entityModel = EntityModel.of(emailViewModel);

        PagedModel<EntityModel<NewEmailViewModel>> pagedModel = PagedModel.of(
                List.of(entityModel),
                new PagedModel.PageMetadata(1, 1, 1)
        );

        when(emailService.findAll(null)).thenReturn(pagedModel);

        ResponseEntity<StatusResponseViewModel> response = emailHandler.handleFindAll(null);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Emails encontrados com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).findAll(null);
    }
}
