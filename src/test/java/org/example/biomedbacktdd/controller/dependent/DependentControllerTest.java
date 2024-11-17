package org.example.biomedbacktdd.controller.dependent;

import org.example.biomedbacktdd.DTO.commands.NewDependentCommand;
import org.example.biomedbacktdd.DTO.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.controllers.dependent.DependentController;
import org.example.biomedbacktdd.entities.dependent.Dependent;
import org.example.biomedbacktdd.handlers.dependent.DependentHandler;
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

class DependentControllerTest {

    @Mock
    private DependentHandler handler;

    @InjectMocks
    private DependentController controller;

    DependentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.ASC, "nomeDep"));

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
        Dependent mockDependent = new Dependent();
        mockDependent.setCpfDep(id);
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
        NewDependentCommand command = new NewDependentCommand();
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
    void testDelete() {
        String id = "123";
        StatusResponseViewModel mockResponse = new StatusResponseViewModel<>();
        mockResponse.setStatus(204);
        mockResponse.setIsOk(true);

        when(handler.handleDelete(id)).thenReturn(ResponseEntity.noContent().build());

        var response = controller.delete(id);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(handler, times(1)).handleDelete(id);
    }

    @Test
    void testFindDependentsByCpfRes() {
        String cpfRes = "12345678900";
        Pageable pageable = mock(Pageable.class);

        StatusResponseViewModel mockResponse = new StatusResponseViewModel<>();
        mockResponse.setStatus(204);
        mockResponse.setIsOk(true);

        when(handler.handleFindDependentsByCpfRes(eq(cpfRes), any(Pageable.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));

        var response = controller.findDependentsByCpfRes(cpfRes, 0, 10, "asc");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(handler, times(1)).handleFindDependentsByCpfRes(eq(cpfRes), any(Pageable.class));
    }

    @Test
    void testUpdate() {
        NewDependentCommand command = new NewDependentCommand();
        StatusResponseViewModel mockResponse = new StatusResponseViewModel<>();
        mockResponse.setStatus(200);
        mockResponse.setIsOk(true);

        when(handler.handleUpdate(command)).thenReturn(ResponseEntity.ok(mockResponse));

        var response = controller.update(command);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(handler, times(1)).handleUpdate(command);
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
        NewDependentCommand command = new NewDependentCommand();

        when(handler.handleCreate(command)).thenReturn(ResponseEntity.notFound().build());

        var response = controller.create(command);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(handler, times(1)).handleCreate(command);
    }

    @Test
    void testDeleteNotFound() {
        String id = "123";

        when(handler.handleDelete(id)).thenReturn(ResponseEntity.notFound().build());

        var response = controller.delete(id);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(handler, times(1)).handleDelete(id);
    }

    @Test
    void testUpdateNotFound() {
        NewDependentCommand command = new NewDependentCommand();

        when(handler.handleUpdate(command)).thenReturn(ResponseEntity.notFound().build());

        var response = controller.update(command);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(handler, times(1)).handleUpdate(command);
    }
}
