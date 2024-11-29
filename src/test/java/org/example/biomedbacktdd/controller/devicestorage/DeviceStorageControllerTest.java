package org.example.biomedbacktdd.controller.devicestorage;

import org.example.biomedbacktdd.VO.auth.DeviceStorageVO;
import org.example.biomedbacktdd.controllers.devicestorage.DeviceStorageController;
import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.devicestorage.DeviceStorageHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeviceStorageControllerTest {

    @Mock
    private DeviceStorageHandler handler;

    @InjectMocks
    private DeviceStorageController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("GET /api/devicestorage/{cpfDep} - Sucesso (200)")
    void testFindDispositivosByCpfDep_Success() {
        String cpfDep = "12345678900";

        StatusResponseViewModel<List<DeviceStorageVO>> successResponse = new StatusResponseViewModel<>();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Operation completed successfully.");

        when(handler.handleFindDispositivosByCpfDep(cpfDep))
                .thenReturn(ResponseEntity.ok(successResponse));

        ResponseEntity<StatusResponseViewModel<List<DeviceStorageVO>>> response = controller.findDispositivosByCpfDep(cpfDep);

        StatusResponseViewModel<List<DeviceStorageVO>> responseBody = response.getBody();

        assertNotNull(response);
        assert responseBody != null;
        assertEquals(200, responseBody.getStatus());
        assertNotNull(response.getBody());
        assertEquals(200, response.getBody().getStatus());
        assertEquals("Operation completed successfully.", response.getBody().getStatusMessage());

        verify(handler, times(1)).handleFindDispositivosByCpfDep(cpfDep);
    }

    @Test
    @DisplayName("GET /api/devicestorage/{cpfDep} - Sem Conteúdo (204)")
    void testFindDispositivosByCpfDep_NoContent() {
        String cpfDep = "00000000000";

        when(handler.handleFindDispositivosByCpfDep(cpfDep))
                .thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<StatusResponseViewModel<List<DeviceStorageVO>>> response = controller.findDispositivosByCpfDep(cpfDep);

        StatusResponseViewModel<List<DeviceStorageVO>> responseBody = response.getBody();
        assertNotNull(response);

        if (responseBody != null) {
            assertEquals(204, responseBody.getStatus());
        }
        assertNull(response.getBody());

        verify(handler, times(1)).handleFindDispositivosByCpfDep(cpfDep);
    }

    @Test
    @DisplayName("GET /api/devicestorage/{cpfDep} - Não Encontrado (404)")
    void testFindDispositivosByCpfDep_NotFound() {
        String cpfDep = "99999999999";

        when(handler.handleFindDispositivosByCpfDep(cpfDep))
                .thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<StatusResponseViewModel<List<DeviceStorageVO>>> response = controller.findDispositivosByCpfDep(cpfDep);

        StatusResponseViewModel<List<DeviceStorageVO>> responseBody = response.getBody();

        assertNotNull(response);
        if (responseBody != null) {
            assertEquals(404, responseBody.getStatus());
        }
        assertNull(response.getBody());

        verify(handler, times(1)).handleFindDispositivosByCpfDep(cpfDep);
    }

    @Test
    @DisplayName("POST /api/devicestorage - Sucesso (200)")
    void testCreateDevice_Success() {
        DeviceStorageCommand command = new DeviceStorageCommand();

        StatusResponseViewModel<DeviceStorageVO> successResponse = new StatusResponseViewModel<>();
        successResponse.setStatus(200);
        successResponse.setStatusMessage("Device created successfully.");

        when(handler.handleCreate(any(DeviceStorageCommand.class)))
                .thenReturn(ResponseEntity.ok(successResponse));

        ResponseEntity<StatusResponseViewModel<DeviceStorageVO>> response = controller.create(command);

        StatusResponseViewModel<DeviceStorageVO> responseBody = response.getBody();
        assertNotNull(response);
        assert responseBody != null;
        assertEquals(200, responseBody.getStatus());
        assertNotNull(response.getBody());
        assertEquals(200, response.getBody().getStatus());
        assertEquals("Device created successfully.", response.getBody().getStatusMessage());

        verify(handler, times(1)).handleCreate(command);
    }

    @Test
    @DisplayName("POST /api/devicestorage - Requisição Inválida (400)")
    void testCreateDevice_BadRequest() {
        DeviceStorageCommand command = new DeviceStorageCommand();

        when(handler.handleCreate(any(DeviceStorageCommand.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        ResponseEntity<StatusResponseViewModel<DeviceStorageVO>> response = controller.create(command);

        StatusResponseViewModel<DeviceStorageVO> responseBody = response.getBody();
        assertNotNull(response);
        if (responseBody != null) {
            assertEquals(400, responseBody.getStatus());
        }
        assertNull(response.getBody());

        verify(handler, times(1)).handleCreate(command);
    }

    @Test
    @DisplayName("POST /api/devicestorage - Erro Interno (500)")
    void testCreateDevice_InternalServerError() {
        DeviceStorageCommand command = new DeviceStorageCommand();

        when(handler.handleCreate(any(DeviceStorageCommand.class)))
                .thenReturn(ResponseEntity.status(500).build());

        ResponseEntity<StatusResponseViewModel<DeviceStorageVO>> response = controller.create(command);

        StatusResponseViewModel<DeviceStorageVO> responseBody = response.getBody();
        assertNotNull(response);
        if (responseBody != null) {
            assertEquals(500, responseBody.getStatus());
        }
        assertNull(response.getBody());

        verify(handler, times(1)).handleCreate(command);
    }
}
