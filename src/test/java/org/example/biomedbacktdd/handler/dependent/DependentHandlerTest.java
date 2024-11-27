package org.example.biomedbacktdd.handler.dependent;

import org.example.biomedbacktdd.dto.commands.NewDependentCommand;
import org.example.biomedbacktdd.dto.results.NewDependentResult;
import org.example.biomedbacktdd.dto.viewmodels.NewDependentViewModel;
import org.example.biomedbacktdd.handlers.dependent.DependentHandler;
import org.example.biomedbacktdd.services.interfaces.dependent.IDependentService;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DependentHandlerTest {

    @Mock
    private IDependentService dependentService;

    @InjectMocks
    private DependentHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleFindAll_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));

        PagedModel<EntityModel<NewDependentViewModel>> mockResponse = PagedModel.of(
                Collections.emptyList(),
                new PagedModel.PageMetadata(10, 0, 0)
        );

        when(dependentService.findAll(pageable)).thenReturn(mockResponse);

        var response = handler.handleFindAll(pageable);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContentResponse() != null);

        verify(dependentService, times(1)).findAll(eq(pageable));
    }

    @Test
    void testHandleCreate_Success() {
        NewDependentCommand command = new NewDependentCommand();
        NewDependentResult mockResult = new NewDependentResult();

        when(dependentService.create(command)).thenReturn(mockResult);

        var response = handler.handleCreate(command);

        assertNotNull(response);
        assertTrue(response.getBody().getIsOk());
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        verify(dependentService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_NotFound() {
        NewDependentCommand command = new NewDependentCommand();

        when(dependentService.create(command)).thenReturn(null);

        var response = handler.handleCreate(command);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(400, response.getBody().getStatus());
        verify(dependentService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_Exception() {
        NewDependentCommand command = new NewDependentCommand();

        when(dependentService.create(any(NewDependentCommand.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        var response = handler.handleCreate(command);

        assertNotNull(response);
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Um erro inesperado aconteceu.", response.getBody().getInfoMessage());

        verify(dependentService, times(1)).create(any(NewDependentCommand.class));
    }

    @Test
    void testHandleDelete_Success() {
        String id = "123";
        when(dependentService.delete(id)).thenReturn(id);

        var response = handler.handleDelete(id);

        assertNotNull(response);
        assertTrue(response.getBody().getIsOk());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(id, response.getBody().getContentResponse());

        verify(dependentService, times(1)).delete(id);
    }

    @Test
    void testHandleDelete_NotFound() {
        String id = "456";

        when(dependentService.delete(id)).thenReturn(null);

        var response = handler.handleDelete(id);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(400, response.getBody().getStatus());
        verify(dependentService, times(1)).delete(id);
    }

    @Test
    void testHandleDelete_Exception() {
        String id = "789";

        when(dependentService.delete(id)).thenThrow(new RuntimeException("Unexpected error"));

        var response = handler.handleDelete(id);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Um erro inesperado aconteceu.", response.getBody().getInfoMessage());

        verify(dependentService, times(1)).delete(id);
    }

    @Test
    void testHandleFindAll_Success_WithResults() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));

        NewDependentViewModel viewModel1 = new NewDependentViewModel();
        viewModel1.setKey("1");
        viewModel1.setNomeDep("John Doe");

        NewDependentViewModel viewModel2 = new NewDependentViewModel();
        viewModel2.setKey("2");
        viewModel2.setNomeDep("Jane Doe");

        List<EntityModel<NewDependentViewModel>> content = Arrays.asList(
                EntityModel.of(viewModel1),
                EntityModel.of(viewModel2)
        );

        PagedModel<EntityModel<NewDependentViewModel>> mockResponse = PagedModel.of(
                content,
                new PagedModel.PageMetadata(2, 0, 2)
        );

        when(dependentService.findAll(pageable)).thenReturn(mockResponse);

        var response = handler.handleFindAll(pageable);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().getIsOk());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getContentResponse().getMetadata().getTotalElements());

        verify(dependentService, times(1)).findAll(eq(pageable));
    }

    @Test
    void testHandleCreate_Success_WithValidInput() {
        NewDependentCommand command = new NewDependentCommand();
        command.setKey("1");
        command.setNomeDep("John Doe");

        NewDependentResult mockResult = new NewDependentResult();
        mockResult.setKey("1");
        mockResult.setNomeDep("John Doe");

        when(dependentService.create(command)).thenReturn(mockResult);

        var response = handler.handleCreate(command);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getIsOk());
        assertEquals("1", response.getBody().getContentResponse().getKey());
        assertEquals("John Doe", response.getBody().getContentResponse().getNomeDep());

        verify(dependentService, times(1)).create(command);
    }

    @Test
    void testHandleCreate_DuplicateEntry() {
        NewDependentCommand command = new NewDependentCommand();

        command.setKey("1");
        command.setNomeDep("John Doe");

        when(dependentService.create(command)).thenThrow(new RuntimeException("Duplicate entry"));

        var response = handler.handleCreate(command);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Um erro inesperado aconteceu.", response.getBody().getInfoMessage());

        verify(dependentService, times(1)).create(command);
    }

    @Test
    void testHandleDelete_Success_WithValidId() {
        String id = "123";
        when(dependentService.delete(id)).thenReturn(id);

        var response = handler.handleDelete(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(id, response.getBody().getContentResponse());

        verify(dependentService, times(1)).delete(id);
    }

    @Test
    void testHandleDelete_BadRequest_WithInvalidIdFormat() {
        String id = "";

        when(dependentService.delete(id)).thenReturn(null);

        var response = handler.handleDelete(id);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro", response.getBody().getInfoMessage());

        verify(dependentService, times(1)).delete(id);
    }

    @Test
    void testHandleDelete_Exception_GenericError() {
        String id = "123";

        when(dependentService.delete(id)).thenThrow(new RuntimeException("Unexpected error"));

        var response = handler.handleDelete(id);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Um erro inesperado aconteceu.", response.getBody().getInfoMessage());

        verify(dependentService, times(1)).delete(id);
    }

    @Test
    void testHandleUpdate_Success() {
        String id = "123";

        NewDependentCommand command = new NewDependentCommand();

        command.setKey("1");
        command.setNomeDep("John Doe Not Updated");

        NewDependentResult mockResult = new NewDependentResult();

        mockResult.setKey(id);
        mockResult.setNomeDep("John Doe Updated");

        when(dependentService.update(command)).thenReturn(mockResult);

        var response = handler.handleUpdate(command);

        assertNotNull(response);
        assertTrue(response.getBody().getIsOk());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("John Doe Updated", response.getBody().getContentResponse().getNomeDep());

        verify(dependentService, times(1)).update(command);
    }

    @Test
    void testHandleUpdate_BadRequest() {
        NewDependentCommand command = new NewDependentCommand();

        command.setKey("1");
        command.setNomeDep("John Doe Updated");

        when(dependentService.update(command)).thenReturn(null);

        var response = handler.handleUpdate(command);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Erro", response.getBody().getInfoMessage());

        verify(dependentService, times(1)).update(command);
    }

    @Test
    void testHandleUpdate_Exception() {
        NewDependentCommand command = new NewDependentCommand();

        command.setKey("1");
        command.setNomeDep("John Doe Updated");

        when(dependentService.update(command)).thenThrow(new RuntimeException("Unexpected error"));

        var response = handler.handleUpdate(command);

        assertNotNull(response);
        assertFalse(response.getBody().getIsOk());
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Um erro inesperado aconteceu.", response.getBody().getInfoMessage());

        verify(dependentService, times(1)).update(command);
    }

    @Test
    void testHandleFindAll_EmptyResponse() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));

        PagedModel<EntityModel<NewDependentViewModel>> mockResponse = PagedModel.of(
                Collections.emptyList(),
                new PagedModel.PageMetadata(10, 0, 0)
        );

        when(dependentService.findAll(pageable)).thenReturn(mockResponse);

        var response = handler.handleFindAll(pageable);

        assertNotNull(response);
        assertTrue(response.getBody().getIsOk());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, response.getBody().getContentResponse().getMetadata().getTotalElements());

        verify(dependentService, times(1)).findAll(eq(pageable));
    }
}
