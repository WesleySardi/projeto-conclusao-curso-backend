package org.example.biomedbacktdd.handler.devicestorage;

import org.example.biomedbacktdd.VO.auth.DeviceStorageVO;
import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.devicestorage.DeviceStorageHandler;
import org.example.biomedbacktdd.services.interfaces.devicestorage.IDeviceStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DeviceStorageHandlerTest {

    @Mock
    private IDeviceStorageService deviceStorageService;

    @InjectMocks
    private DeviceStorageHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("handleCreate - Sucesso (200)")
    void testHandleCreate_Success() {
        // Arrange
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setTokenDispositivo("device123token");
        command.setCpfResponsavel("12345678900");

        DeviceStorageVO createdDevice = new DeviceStorageVO(
                "device123",
                "device123token",
                "12345678900",
                "Smartphone"
        );
        when(deviceStorageService.createDevice(any(DeviceStorageCommand.class))).thenReturn(createdDevice);

        // Act
        ResponseEntity<StatusResponseViewModel<DeviceStorageVO>> response = handler.handleCreate(command);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<DeviceStorageVO> responseBody = response.getBody();
        assertEquals("Sucesso", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Sucesso'");
        assertEquals("Dispositivo registrado com sucesso.", responseBody.getStatusMessage(), "O statusMessage no corpo deve corresponder");
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals(createdDevice, responseBody.getContentResponse(), "Os dados retornados devem corresponder ao dispositivo criado");

        verify(deviceStorageService, times(1)).createDevice(command);
    }


    @Test
    @DisplayName("handleCreate - Erro Interno do Servidor (500)")
    void testHandleCreate_InternalServerError() {
        // Arrange
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setTokenDispositivo("device123token");
        command.setCpfResponsavel("12345678900");

        String exceptionMessage = "Erro interno do servidor.";
        when(deviceStorageService.createDevice(any(DeviceStorageCommand.class))).thenThrow(new RuntimeException(exceptionMessage));

        // Act
        ResponseEntity<StatusResponseViewModel<DeviceStorageVO>> response = handler.handleCreate(command);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "O status da resposta deve ser 500 Internal Server Error");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<DeviceStorageVO> responseBody = response.getBody();
        assertEquals("Erro", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Erro'");
        assertEquals(exceptionMessage, responseBody.getStatusMessage(), "A mensagem de erro deve corresponder");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus(), "O código de status no corpo deve ser 500");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        verify(deviceStorageService, times(1)).createDevice(command);
    }


    @Test
    @DisplayName("handleFindDispositivosByCpfDep - Sucesso (200)")
    void testHandleFindDispositivosByCpfDep_Success() {
        // Arrange
        String cpfDep = "12345678900";
        List<DeviceStorageVO> devices = Arrays.asList(
                new DeviceStorageVO("device123", "token123", "12345678900", "Smartphone"),
                new DeviceStorageVO("device124", "token124", "12345678900", "Tablet")
        );
        when(deviceStorageService.findDispositivosByCpfDep(anyString())).thenReturn(devices);

        // Act
        ResponseEntity<StatusResponseViewModel<List<DeviceStorageVO>>> response = handler.handleFindDispositivosByCpfDep(cpfDep);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O status da resposta deve ser 200 OK");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<List<DeviceStorageVO>> responseBody = response.getBody();
        assertEquals("Sucesso", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Sucesso'");
        assertEquals("Dispositivos encontrados com sucesso.", responseBody.getStatusMessage(), "O statusMessage no corpo deve corresponder");
        assertEquals(HttpStatus.OK.value(), responseBody.getStatus(), "O código de status no corpo deve ser 200");
        assertTrue(responseBody.getIsOk(), "O campo 'isOk' deve ser verdadeiro");
        assertEquals(devices, responseBody.getContentResponse(), "Os dados retornados devem corresponder à lista de dispositivos");

        verify(deviceStorageService, times(1)).findDispositivosByCpfDep(cpfDep);
    }


    @Test
    @DisplayName("handleFindDispositivosByCpfDep - Não Encontrado (404)")
    void testHandleFindDispositivosByCpfDep_NotFound() {
        // Arrange
        String cpfDep = "00000000000";
        String exceptionMessage = "Dispositivos não encontrados para o CPF fornecido";
        when(deviceStorageService.findDispositivosByCpfDep(anyString())).thenThrow(new RuntimeException(exceptionMessage));

        // Act
        ResponseEntity<StatusResponseViewModel<List<DeviceStorageVO>>> response = handler.handleFindDispositivosByCpfDep(cpfDep);

        // Assert
        assertNotNull(response, "A resposta não deve ser nula");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "O status da resposta deve ser 404 Not Found");
        assertNotNull(response.getBody(), "O corpo da resposta não deve ser nulo");

        StatusResponseViewModel<List<DeviceStorageVO>> responseBody = response.getBody();
        assertEquals("Erro", responseBody.getInfoMessage(), "O infoMessage no corpo deve ser 'Erro'");
        assertEquals(exceptionMessage, responseBody.getStatusMessage(), "A mensagem de erro deve corresponder");
        assertEquals(HttpStatus.NOT_FOUND.value(), responseBody.getStatus(), "O código de status no corpo deve ser 404");
        assertFalse(responseBody.getIsOk(), "O campo 'isOk' deve ser falso");
        assertNull(responseBody.getContentResponse(), "Os dados retornados devem ser nulos em caso de erro");

        verify(deviceStorageService, times(1)).findDispositivosByCpfDep(cpfDep);
    }

}
