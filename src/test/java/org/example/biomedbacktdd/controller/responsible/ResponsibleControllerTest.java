package org.example.biomedbacktdd.controller.responsible;

import org.example.biomedbacktdd.dto.commands.NewResponsibleCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.controllers.responsible.ResponsibleController;
import org.example.biomedbacktdd.handlers.responsible.ResponsibleHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResponsibleControllerTest {

    @Mock
    private ResponsibleHandler handler;

    @InjectMocks
    private ResponsibleController controller;

    ResponsibleControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "nomeRes"));

        StatusResponseViewModel mockResponse = new StatusResponseViewModel<>();
        mockResponse.setStatus(200);
        mockResponse.setIsOk(true);

        when(handler.handleFindAll(pageable)).thenReturn(ResponseEntity.ok(mockResponse));

        var response = controller.findAll(0, 12, "asc");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        verify(handler, times(1)).handleFindAll(eq(pageable));
    }

    @Test
    void testFindById() {
        String id = "123";
        StatusResponseViewModel mockResponse = new StatusResponseViewModel<>();
        mockResponse.setStatus(200);
        mockResponse.setIsOk(true);

        when(handler.handleFindById(id)).thenReturn(ResponseEntity.ok(mockResponse));

        var response = controller.findById(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(handler, times(1)).handleFindById(id);
    }

    @Test
    void testCreate() {
        NewResponsibleCommand command = new NewResponsibleCommand();
        StatusResponseViewModel mockResponse = new StatusResponseViewModel<>();
        mockResponse.setStatus(200);
        mockResponse.setIsOk(true);

        when(handler.handleCreate(command)).thenReturn(ResponseEntity.ok(mockResponse));

        var response = controller.create(command);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(handler, times(1)).handleCreate(command);
    }

    @Test
    void testFindByIdNotFound() {
        String id = "123";

        when(handler.handleFindById(id)).thenReturn(ResponseEntity.notFound().build());

        var response = controller.findById(id);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(handler, times(1)).handleFindById(id);
    }

    @Test
    void testCreateNotFound() {
        NewResponsibleCommand command = new NewResponsibleCommand();

        when(handler.handleCreate(command)).thenReturn(ResponseEntity.notFound().build());

        var response = controller.create(command);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(handler, times(1)).handleCreate(command);
    }
}
