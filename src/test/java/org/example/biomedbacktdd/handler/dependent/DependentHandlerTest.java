package org.example.biomedbacktdd.handler.dependent;

import org.example.biomedbacktdd.DTO.commands.NewDependentCommand;
import org.example.biomedbacktdd.handlers.dependent.DependentHandler;
import org.example.biomedbacktdd.services.interfaces.dependent.IDependentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DependentHandlerTest {

    @Mock
    private IDependentService dependentService;

    @InjectMocks
    private DependentHandler handler;

    DependentHandlerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleFindAll_Success() {
        Pageable pageable = mock(Pageable.class);
        when(dependentService.findAll(pageable)).thenReturn(null);

        var response = handler.handleFindAll(pageable);

        assertNotNull(response);
        verify(dependentService, times(1)).findAll(pageable);
    }

    @Test
    void testHandleCreate_Success() {
        // Arrange
        NewDependentCommand command = new NewDependentCommand();
        when(dependentService.create(command)).thenReturn(null);

        // Act
        var response = handler.handleCreate(command);

        // Assert
        assertNotNull(response);
        verify(dependentService, times(1)).create(command);
    }

    @Test
    void testHandleDelete_Success() {
        String id = "123";
        when(dependentService.delete(id)).thenReturn(id);

        var response = handler.handleDelete(id);

        assertNotNull(response);
        verify(dependentService, times(1)).delete(id);
    }
}
