package org.example.biomedbacktdd.handler.responsible;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.results.NewResponsibleResult;
import org.example.biomedbacktdd.dto.viewmodels.NewResponsibleViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.responsible.ResponsibleHandler;
import org.example.biomedbacktdd.services.interfaces.responsible.IResponsibleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

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
    void testHandleFindAll() {
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "nomeRes"));

        PagedModel<EntityModel<NewResponsibleViewModel>> mockResponse = PagedModel.of(
                Collections.emptyList(),
                new PagedModel.PageMetadata(12, 0, 0)
        );

        when(responsibleService.findAll(pageable)).thenReturn(mockResponse);

        var response = responsibleHandler.handleFindAll(pageable);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContentResponse() != null);

        verify(responsibleService, times(1)).findAll(eq(pageable));
    }

    @Test
    void testHandleFindById() {
        String id = "123";
        NewResponsibleViewModel mockViewModel = new NewResponsibleViewModel();
        mockViewModel.setKey(id);

        when(responsibleService.findById(id)).thenReturn(mockViewModel);

        ResponseEntity<StatusResponseViewModel<NewResponsibleViewModel>> response = responsibleHandler.handleFindById(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(id, response.getBody().getContentResponse().getKey());

        verify(responsibleService, times(1)).findById(id);
    }

    @Test
    void testHandleFindById_NotFound() {
        String id = "371237128";

        when(responsibleService.findById(id)).thenReturn(null);

        var response = responsibleHandler.handleFindById(id);

        assertNotNull(response);
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro ao encontrar o responsavel.", response.getBody().getStatusMessage());

        verify(responsibleService, times(1)).findById(id);
    }

    @Test
    void testHandleCreate_Success() {
        NewResponsibleCommand command = new NewResponsibleCommand();
        NewResponsibleResult mockResult = new NewResponsibleResult();

        when(responsibleService.create(command)).thenReturn(mockResult);

        var response = responsibleHandler.handleCreate(command);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Responsavel criado com sucesso.", response.getBody().getStatusMessage());

        verify(responsibleService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_NotFound() {
        NewResponsibleCommand command = new NewResponsibleCommand();

        when(responsibleService.create(command)).thenReturn(null);

        var response = responsibleHandler.handleCreate(command);

        assertNotNull(response);
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro ao criar o responsavel.", response.getBody().getStatusMessage());

        verify(responsibleService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_Exception() {
        NewResponsibleCommand command = new NewResponsibleCommand();

        when(responsibleService.create(any(NewResponsibleCommand.class)))
                .thenThrow(new RuntimeException("Erro inesperado"));

        var response = responsibleHandler.handleCreate(command);

        assertNotNull(response);
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Um erro inesperado aconteceu.", response.getBody().getInfoMessage());

        verify(responsibleService, times(1)).create(any(NewResponsibleCommand.class));
    }

    @Test
    void testHandleFindByEmail() {
        String email = "test@example.com";
        NewResponsibleViewModel mockViewModel = new NewResponsibleViewModel();
        mockViewModel.setEmailRes(email);

        when(responsibleService.findByEmail(email)).thenReturn(mockViewModel);

        var response = responsibleHandler.handleFindByEmail(email);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(email, response.getBody().getContentResponse().getEmailRes());

        verify(responsibleService, times(1)).findByEmail(email);
    }

    @Test
    void testHandleFindByEmail_NotFound() {
        String email = "nonexistent@example.com";

        when(responsibleService.findByEmail(email)).thenReturn(null);

        var response = responsibleHandler.handleFindByEmail(email);

        assertNotNull(response);
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro ao encontrar o responsavel.", response.getBody().getStatusMessage());

        verify(responsibleService, times(1)).findByEmail(email);
    }

    @Test
    void testHandleFindAll_EmptyList() {
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "nomeRes"));

        PagedModel<EntityModel<NewResponsibleViewModel>> emptyResponse = PagedModel.of(
                Collections.emptyList(),
                new PagedModel.PageMetadata(12, 0, 0)
        );

        when(responsibleService.findAll(pageable)).thenReturn(emptyResponse);

        var response = responsibleHandler.handleFindAll(pageable);

        assertNotNull(response);
        assertEquals(200, response.getBody().getStatus());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContentResponse().getMetadata().getTotalElements() == 0);

        verify(responsibleService, times(1)).findAll(eq(pageable));
    }
}
