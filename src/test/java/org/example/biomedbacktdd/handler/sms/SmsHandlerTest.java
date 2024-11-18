package org.example.biomedbacktdd.handler.sms;

import org.example.biomedbacktdd.dto.commands.NewSmsCommand;
import org.example.biomedbacktdd.dto.results.NewSmsResult;
import org.example.biomedbacktdd.dto.viewmodels.NewSmsViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.sms.SmsHandlerHandler;
import org.example.biomedbacktdd.services.interfaces.sms.ISmsHandlerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SmsHandlerTest {

    private SmsHandlerHandler smsHandlerHandler;
    private ISmsHandlerService smsHandlerService;

    @BeforeEach
    void setUp() {
        smsHandlerService = mock(ISmsHandlerService.class);
        smsHandlerHandler = new SmsHandlerHandler(smsHandlerService);
    }

    @Test
    void testHandleFindAll_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        var pagedModel = PagedModel.of(
                List.of(EntityModel.of(new NewSmsViewModel())),
                new PagedModel.PageMetadata(10, 0, 1)
        );
        when(smsHandlerService.findAll(pageable)).thenReturn(pagedModel);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleFindAll(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getIsOk());
        assertEquals("SMS encontrado com sucesso.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleFindAll_Error() {
        Pageable pageable = PageRequest.of(0, 12);
        var pagedModel = PagedModel.of(
                List.of(EntityModel.of(new NewSmsViewModel())),
                new PagedModel.PageMetadata(12, 0, 1)
        );
        when(smsHandlerService.findAll(null)).thenReturn(pagedModel);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleFindAll(pageable);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getIsOk());
        assertEquals("Erro ao encontrar o SMS.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleFindById_Success() {
        Integer smsId = 1;
        var viewModel = new NewSmsViewModel();
        when(smsHandlerService.findById(smsId)).thenReturn(viewModel);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleFindById(smsId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getIsOk());
        assertEquals("SMS encontrado com sucesso.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleFindById_Error() {
        Integer smsId = 1;
        var viewModel = new NewSmsViewModel();
        when(smsHandlerService.findById(null)).thenReturn(viewModel);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleFindById(smsId);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getIsOk());
        assertEquals("Erro ao encontrar o SMS.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleCreate_Success() {
        var newSms = new NewSmsCommand();
        var result = new NewSmsResult();
        when(smsHandlerService.create(newSms)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleCreate(newSms);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getIsOk());
        assertEquals("SMS criado com sucesso.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleCreate_Error() {
        var newSms = new NewSmsCommand();
        var result = new NewSmsResult();
        when(smsHandlerService.create(null)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleCreate(newSms);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getIsOk());
        assertEquals("Erro ao criar o SMS.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleUpdate_Success() {
        var smsCode = 123;
        var returnDate = new Timestamp(System.currentTimeMillis());
        var result = new NewSmsResult();
        when(smsHandlerService.update(smsCode, returnDate)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleUpdate(smsCode, returnDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getIsOk());
        assertEquals("SMS alterado com sucesso.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleUpdate_Error() {
        var smsCode = 123;
        var returnDate = new Timestamp(System.currentTimeMillis());
        var result = new NewSmsResult();
        when(smsHandlerService.update(null, returnDate)).thenReturn(result);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleUpdate(smsCode, returnDate);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getIsOk());
        assertEquals("Erro ao alterar o SMS.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleDelete_Success() {
        Integer smsId = 1;
        when(smsHandlerService.delete(smsId)).thenReturn(1);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleDelete(smsId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getIsOk());
        assertEquals("SMS deletado com sucesso.", response.getBody().getStatusMessage());
    }

    @Test
    void testHandleDelete_Error() {
        Integer smsId = 1;
        when(smsHandlerService.delete(smsId)).thenReturn(null);

        ResponseEntity<StatusResponseViewModel> response = smsHandlerHandler.handleDelete(smsId);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getIsOk());
        assertEquals("Erro ao deletar o SMS.", response.getBody().getStatusMessage());
    }
}
