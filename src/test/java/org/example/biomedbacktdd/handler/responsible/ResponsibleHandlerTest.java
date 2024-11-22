package org.example.biomedbacktdd.handler.responsible;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.results.NewResponsibleResult;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.responsible.ResponsibleHandler;
import org.example.biomedbacktdd.services.interfaces.responsible.IResponsibleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResponsibleHandlerTest {

    @Mock
    private IResponsibleService responsibleService;

    @InjectMocks
    private ResponsibleHandler responsibleHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleCreate_Success() {
        NewResponsibleCommand command = new NewResponsibleCommand();
        NewResponsibleResult expectedResponse = new NewResponsibleResult();

        when(responsibleService.create(command)).thenReturn(expectedResponse);

        ResponseEntity<StatusResponseViewModel> response = responsibleHandler.handleCreate(command);

        assertNotNull(response);
        assertEquals(200, response.getBody().getStatus());
        assertEquals("Sucesso", response.getBody().getInfoMessage());
        assertEquals("Responsavel criado com sucesso.", response.getBody().getStatusMessage());

        verify(responsibleService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_Failure() {
        NewResponsibleCommand command = new NewResponsibleCommand();
        NewResponsibleResult errorResponse = null;

        when(responsibleService.create(command)).thenReturn(errorResponse);

        ResponseEntity<StatusResponseViewModel> response = responsibleHandler.handleCreate(command);

        assertNotNull(response);
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro", response.getBody().getInfoMessage());
        assertEquals("Erro ao criar o responsavel.", response.getBody().getStatusMessage());

        verify(responsibleService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_Exception() {
        NewResponsibleCommand command = new NewResponsibleCommand();

        when(responsibleService.create(any(NewResponsibleCommand.class)))
                .thenThrow(new RuntimeException("Erro inesperado"));

        ResponseEntity<StatusResponseViewModel> responseEntity = responsibleHandler.handleCreate(command);

        assertNotNull(responseEntity);
        assertEquals(500, responseEntity.getBody().getStatus());
        assertEquals("Um erro inesperado aconteceu.", responseEntity.getBody().getInfoMessage());
        assertEquals("Erro inesperado", responseEntity.getBody().getStatusMessage());

        verify(responsibleService, times(1)).create(any(NewResponsibleCommand.class));
    }

}
