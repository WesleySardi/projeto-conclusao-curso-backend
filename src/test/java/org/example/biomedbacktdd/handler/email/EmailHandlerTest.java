package org.example.biomedbacktdd.handler.email;

import org.example.biomedbacktdd.dto.commands.NewEmailCommand;
import org.example.biomedbacktdd.dto.results.NewEmailResult;
import org.example.biomedbacktdd.dto.viewmodels.NewEmailViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
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
    void testHandleCreate_Success() {
        Timestamp sendDate = Timestamp.valueOf("2024-11-17 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2024-11-17 10:30:00");
        NewEmailCommand command = new NewEmailCommand(1234, sendDate, returnDate, "test@example.com", "12345678901");

        NewEmailResult result = new NewEmailResult();

        when(emailService.create(command)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel<NewEmailResult>> response = emailHandler.handleCreate(command);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Email criado com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).create(command);
    }

    @Test
    void testHandleVerifyEmailCode_Success() {
        when(emailService.verifyEmailCode("test@example.com", 1234)).thenReturn(true);

        ResponseEntity<StatusResponseViewModel<Boolean>> response = emailHandler.handleVerifyEmailCode("test@example.com", 1234);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Código do email válido.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).verifyEmailCode("test@example.com", 1234);
    }

    @Test
    void testHandleVerifyEmailCode_Fail() {
        when(emailService.verifyEmailCode("test@example.com", 1234)).thenReturn(false);

        ResponseEntity<StatusResponseViewModel<Boolean>> response = emailHandler.handleVerifyEmailCode("test@example.com", 1234);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Código do email inválido.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).verifyEmailCode("test@example.com", 1234);
    }

    @Test
    void testHandleFindAll_Success() {
        NewEmailViewModel emailViewModel = new NewEmailViewModel();
        EntityModel<NewEmailViewModel> entityModel = EntityModel.of(emailViewModel);

        PagedModel<EntityModel<NewEmailViewModel>> pagedModel = PagedModel.of(
                List.of(entityModel),
                new PagedModel.PageMetadata(1, 1, 1)
        );

        when(emailService.findAll(null)).thenReturn(pagedModel);

        ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewEmailViewModel>>>> response = emailHandler.handleFindAll(null);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Emails encontrados com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).findAll(null);
    }

    @Test
    void testHandleCreate_Failure() {
        Timestamp sendDate = Timestamp.valueOf("2024-11-17 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2024-11-17 10:30:00");
        NewEmailCommand command = new NewEmailCommand(1234, sendDate, returnDate, "test@example.com", "12345678901");

        when(emailService.create(command)).thenReturn(null);

        ResponseEntity<StatusResponseViewModel<NewEmailResult>> response = emailHandler.handleCreate(command);

        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro ao criar email.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).create(command);
    }

    @Test
    void testHandleFindById_Success() {
        NewEmailViewModel emailViewModel = new NewEmailViewModel();
        when(emailService.findById(1)).thenReturn(emailViewModel);

        ResponseEntity<StatusResponseViewModel<NewEmailViewModel>> response = emailHandler.handleFindById(1);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Email encontrado com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).findById(1);
    }

    @Test
    void testHandleFindById_Failure() {
        when(emailService.findById(1)).thenReturn(null);

        ResponseEntity<StatusResponseViewModel<NewEmailViewModel>> response = emailHandler.handleFindById(1);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Erro ao encontrar o email.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).findById(1);
    }

    @Test
    void testHandleSendQrCodeWithSendGrid_Success() {
        when(emailService.sendQrCodeWithSendGrid("test@example.com")).thenReturn("QR Code enviado com sucesso.");

        ResponseEntity<StatusResponseViewModel<String>> response = emailHandler.handleSendQrCodeWithSendGrid("test@example.com");

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("QR code enviado com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).sendQrCodeWithSendGrid("test@example.com");
    }

    @Test
    void testHandleSendQrCodeWithSendGrid_Failure() {
        when(emailService.sendQrCodeWithSendGrid("test@example.com")).thenReturn(null);

        ResponseEntity<StatusResponseViewModel<String>> response = emailHandler.handleSendQrCodeWithSendGrid("test@example.com");

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Erro ao enviar o QR code.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).sendQrCodeWithSendGrid("test@example.com");
    }

    @Test
    void testHandleFindAll_NoEmails() {
        PagedModel<EntityModel<NewEmailViewModel>> emptyPagedModel = PagedModel.of(
                List.of(),
                new PagedModel.PageMetadata(0, 0, 0)
        );

        when(emailService.findAll(null)).thenReturn(emptyPagedModel);

        ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewEmailViewModel>>>> response = emailHandler.handleFindAll(null);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Emails encontrados com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).findAll(null);
    }

    @Test
    void testHandleCreate_Exception() {
        Timestamp sendDate = Timestamp.valueOf("2024-11-17 10:00:00");
        Timestamp returnDate = Timestamp.valueOf("2024-11-17 10:30:00");
        NewEmailCommand command = new NewEmailCommand(1234, sendDate, returnDate, "test@example.com", "12345678901");

        when(emailService.create(command)).thenThrow(new RuntimeException("Erro inesperado."));

        ResponseEntity<StatusResponseViewModel<NewEmailResult>> response = emailHandler.handleCreate(command);

        assertEquals(500, response.getBody().getStatus());
        assertEquals("Erro inesperado.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_InvalidCommand() {
        NewEmailCommand command = new NewEmailCommand(0, null, null, "", "");

        ResponseEntity<StatusResponseViewModel<NewEmailResult>> response = emailHandler.handleCreate(command);

        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro ao criar email.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleFindAll_MultiplePages() {
        NewEmailViewModel emailViewModel1 = new NewEmailViewModel();
        NewEmailViewModel emailViewModel2 = new NewEmailViewModel();

        EntityModel<NewEmailViewModel> entityModel1 = EntityModel.of(emailViewModel1);
        EntityModel<NewEmailViewModel> entityModel2 = EntityModel.of(emailViewModel2);

        PagedModel<EntityModel<NewEmailViewModel>> pagedModel = PagedModel.of(
                List.of(entityModel1, entityModel2),
                new PagedModel.PageMetadata(2, 1, 2)
        );

        when(emailService.findAll(null)).thenReturn(pagedModel);

        ResponseEntity<StatusResponseViewModel<PagedModel<EntityModel<NewEmailViewModel>>>> response = emailHandler.handleFindAll(null);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Emails encontrados com sucesso.", response.getBody().getStatusMessage());

        verify(emailService, times(1)).findAll(null);
    }

}
