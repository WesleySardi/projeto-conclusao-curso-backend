package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.results.DeviceStorageResult;
import org.example.biomedbacktdd.entities.devicestorage.DeviceStorage;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.exceptions.ServiceException;
import org.example.biomedbacktdd.repositories.interfaces.devicestorage.IDeviceStorageRepository;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.example.biomedbacktdd.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceStorageServiceTest {

    private IDeviceStorageRepository deviceStorageRepository;
    private IResponsibleRepository responsibleRepository;
    private DeviceStorageService deviceStorageService;

    @BeforeEach
    void setUp() {
        deviceStorageRepository = mock(IDeviceStorageRepository.class);
        responsibleRepository = mock(IResponsibleRepository.class);
        MapperUtil mapperUtil = mock(MapperUtil.class);
        deviceStorageService = new DeviceStorageService(deviceStorageRepository, responsibleRepository, mapperUtil);
    }

    @Test
    void testFindDispositivosByCpfDep_Success() {
        // Dado
        String cpfDep = "12345678900";
        List<Object[]> dispositivosDoResponsavel = getObjects();

        when(deviceStorageRepository.findTokenDispositivosByCpfDep(cpfDep)).thenReturn(dispositivosDoResponsavel);

        // Quando
        List<DeviceStorageResult> result = deviceStorageService.findDispositivosByCpfDep(cpfDep);

        // Então
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("token1", result.get(0).getTokenDispositivo());
        assertEquals("token1", result.get(1).getTokenDispositivo());
        // Todos retornam o mesmo responsável conforme a lógica atual do service
        assertEquals("12345678900", result.get(0).getCpfResponsavel());
        assertEquals("12345678900", result.get(1).getCpfResponsavel());

        verify(deviceStorageRepository, times(1)).findTokenDispositivosByCpfDep(cpfDep);
    }

    private static List<Object[]> getObjects() {
        DeviceStorage device1 = new DeviceStorage();
        device1.setId(1);
        device1.setTokenDispositivo("token1");

        DeviceStorage device2 = new DeviceStorage();
        device2.setId(2);
        device2.setTokenDispositivo("token2");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        Object[] row1 = {device1, responsible};
        Object[] row2 = {device2, responsible};
        return Arrays.asList(row1, row2);
    }

    @Test
    void testCreateDevice_ExistingDevice_DifferentResponsible_Error() {
        // Dado
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setCpfResponsavel("12345678900");
        command.setTokenDispositivo("token123");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        Responsible otherResponsible = new Responsible();
        otherResponsible.setCpfRes("09876543211");

        when(responsibleRepository.findResponsibleByCpf(command.getCpfResponsavel()))
                .thenReturn(Optional.of(responsible));

        DeviceStorage existingDevice = new DeviceStorage();
        existingDevice.setId(1);
        existingDevice.setTokenDispositivo(command.getTokenDispositivo());
        existingDevice.setResponsavel(otherResponsible);

        Object[] row = {existingDevice, otherResponsible};
        when(deviceStorageRepository.findByTokenDispositivo(command.getTokenDispositivo()))
                .thenReturn(List.<Object[]>of(row));

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            deviceStorageService.createDevice(command);
        });

        assertEquals("Este token já está associado a outro responsável.", exception.getMessage());

        verify(responsibleRepository, times(1)).findResponsibleByCpf(command.getCpfResponsavel());
        verify(deviceStorageRepository, times(1)).findByTokenDispositivo(command.getTokenDispositivo());
        verify(deviceStorageRepository, never()).save(any(DeviceStorage.class));
    }

    @Test
    void testCreateDevice_NullCpfResponsavel_Error() {
        // Dado
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setTokenDispositivo("token123");

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            deviceStorageService.createDevice(command);
        });

        assertEquals("O CPF do responsável não pode ser nulo.", exception.getMessage());

        verify(responsibleRepository, never()).findResponsibleByCpf(anyString());
        verify(deviceStorageRepository, never()).findByTokenDispositivo(anyString());
        verify(deviceStorageRepository, never()).save(any(DeviceStorage.class));
    }

    @Test
    void testCreateDevice_ResponsibleNotFound_Error() {
        // Dado
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setCpfResponsavel("12345678900");
        command.setTokenDispositivo("token123");

        when(responsibleRepository.findResponsibleByCpf(command.getCpfResponsavel()))
                .thenReturn(Optional.empty());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            deviceStorageService.createDevice(command);
        });

        assertEquals(DeviceStorageService.RESPONSIBLE_NOT_FOUND + command.getCpfResponsavel(), exception.getMessage());

        verify(responsibleRepository, times(1)).findResponsibleByCpf(command.getCpfResponsavel());
        verify(deviceStorageRepository, never()).findByTokenDispositivo(anyString());
        verify(deviceStorageRepository, never()).save(any(DeviceStorage.class));
    }

    @Test
    void testFindDispositivosByCpfRes_Success() {
        // Dado
        String cpfRes = "12345678900";
        List<Object[]> dispositivosDoResponsavel = getObjects(); // Reuso do método getObjects() ou algo similar

        when(deviceStorageRepository.findTokenDispositivosByCpfRes(cpfRes)).thenReturn(dispositivosDoResponsavel);

        // Quando
        List<DeviceStorageResult> result = deviceStorageService.findDispositivosByCpfRes(cpfRes);

        // Então
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("token1", result.get(0).getTokenDispositivo());
        assertEquals("token2", result.get(1).getTokenDispositivo());
        // Todos retornam o mesmo responsável conforme a lógica atual do service
        assertEquals("12345678900", result.get(0).getCpfResponsavel());
        assertEquals("12345678900", result.get(1).getCpfResponsavel());

        verify(deviceStorageRepository, times(1)).findTokenDispositivosByCpfRes(cpfRes);
    }

    @Test
    void testFindDispositivosByCpfRes_NotFound() {
        // Dado
        String cpfRes = "00000000000";
        when(deviceStorageRepository.findTokenDispositivosByCpfRes(cpfRes)).thenReturn(Collections.emptyList());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            deviceStorageService.findDispositivosByCpfRes(cpfRes);
        });

        assertTrue(exception.getMessage().contains(DeviceStorageService.DEVICE_NOT_FOUND));
        assertTrue(exception.getMessage().contains(cpfRes));

        verify(deviceStorageRepository, times(1)).findTokenDispositivosByCpfRes(cpfRes);
        verify(deviceStorageRepository, never()).save(any(DeviceStorage.class));
    }

}
