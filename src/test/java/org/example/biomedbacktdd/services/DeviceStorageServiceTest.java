package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.vo.DeviceStorageVO;
import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
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
    private MapperUtil mapperUtil;
    private DeviceStorageService deviceStorageService;

    @BeforeEach
    void setUp() {
        deviceStorageRepository = mock(IDeviceStorageRepository.class);
        responsibleRepository = mock(IResponsibleRepository.class);
        mapperUtil = mock(MapperUtil.class);
        deviceStorageService = new DeviceStorageService(deviceStorageRepository, responsibleRepository, mapperUtil);
    }

    @Test
    void testFindDispositivosByCpfDep_Success() {
        // Dado
        String cpfDep = "12345678900";
        DeviceStorage device1 = new DeviceStorage();
        device1.setId(1);
        device1.setTokenDispositivo("token1");

        DeviceStorage device2 = new DeviceStorage();
        device2.setId(2);
        device2.setTokenDispositivo("token2");

        List<DeviceStorage> devices = Arrays.asList(device1, device2);

        when(deviceStorageRepository.findTokenDispositivosByCpfDep(cpfDep)).thenReturn(devices);

        DeviceStorageVO vo1 = new DeviceStorageVO();
        vo1.setId(1);
        vo1.setTokenDispositivo("token1");

        DeviceStorageVO vo2 = new DeviceStorageVO();
        vo2.setId(2);
        vo2.setTokenDispositivo("token2");

        when(mapperUtil.map(device1, DeviceStorageVO.class)).thenReturn(vo1);
        when(mapperUtil.map(device2, DeviceStorageVO.class)).thenReturn(vo2);

        // Quando
        List<DeviceStorageVO> result = deviceStorageService.findDispositivosByCpfDep(cpfDep);

        // Então
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("token1", result.get(0).getTokenDispositivo());
        assertEquals("token2", result.get(1).getTokenDispositivo());

        verify(deviceStorageRepository, times(1)).findTokenDispositivosByCpfDep(cpfDep);
        verify(mapperUtil, times(1)).map(device1, DeviceStorageVO.class);
        verify(mapperUtil, times(1)).map(device2, DeviceStorageVO.class);
    }

    @Test
    void testFindDispositivosByCpfDep_NotFound() {
        // Dado
        String cpfDep = "12345678900";
        when(deviceStorageRepository.findTokenDispositivosByCpfDep(cpfDep)).thenReturn(Collections.emptyList());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            deviceStorageService.findDispositivosByCpfDep(cpfDep);
        });

        assertEquals(DeviceStorageService.DEVICE_NOT_FOUND + cpfDep, exception.getMessage());
        verify(deviceStorageRepository, times(1)).findTokenDispositivosByCpfDep(cpfDep);
    }

    @Test
    void testCreateDevice_NewDevice_Success() {
        // Dado
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setCpfResponsavel("12345678900");
        command.setTokenDispositivo("token123");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        when(responsibleRepository.findResponsibleByCpf(command.getCpfResponsavel()))
                .thenReturn(Optional.of(responsible));

        when(deviceStorageRepository.findByTokenDispositivo(command.getTokenDispositivo()))
                .thenReturn(Optional.empty());

        DeviceStorage deviceStorage = new DeviceStorage();
        deviceStorage.setId(1);
        deviceStorage.setTokenDispositivo(command.getTokenDispositivo());
        deviceStorage.setResponsavel(responsible);

        when(deviceStorageRepository.save(any(DeviceStorage.class))).thenReturn(deviceStorage);

        DeviceStorageVO deviceStorageVO = new DeviceStorageVO();
        deviceStorageVO.setId(1);
        deviceStorageVO.setTokenDispositivo("token123");

        when(mapperUtil.map(deviceStorage, DeviceStorageVO.class)).thenReturn(deviceStorageVO);

        // Quando
        DeviceStorageVO result = deviceStorageService.createDevice(command);

        // Então
        assertNotNull(result);
        assertEquals("token123", result.getTokenDispositivo());

        verify(responsibleRepository, times(1)).findResponsibleByCpf(command.getCpfResponsavel());
        verify(deviceStorageRepository, times(1)).findByTokenDispositivo(command.getTokenDispositivo());
        verify(deviceStorageRepository, times(1)).save(any(DeviceStorage.class));
        verify(mapperUtil, times(1)).map(deviceStorage, DeviceStorageVO.class);
    }

    @Test
    void testCreateDevice_ExistingDevice_SameResponsible_Success() {
        // Dado
        DeviceStorageCommand command = new DeviceStorageCommand();
        command.setCpfResponsavel("12345678900");
        command.setTokenDispositivo("token123");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        when(responsibleRepository.findResponsibleByCpf(command.getCpfResponsavel()))
                .thenReturn(Optional.of(responsible));

        DeviceStorage existingDevice = new DeviceStorage();
        existingDevice.setId(1);
        existingDevice.setTokenDispositivo(command.getTokenDispositivo());
        existingDevice.setResponsavel(responsible);
        existingDevice.setDataCadastro(new Date());

        when(deviceStorageRepository.findByTokenDispositivo(command.getTokenDispositivo()))
                .thenReturn(Optional.of(existingDevice));

        when(deviceStorageRepository.save(existingDevice)).thenReturn(existingDevice);

        DeviceStorageVO deviceStorageVO = new DeviceStorageVO();
        deviceStorageVO.setId(1);
        deviceStorageVO.setTokenDispositivo("token123");

        when(mapperUtil.map(existingDevice, DeviceStorageVO.class)).thenReturn(deviceStorageVO);

        // Quando
        DeviceStorageVO result = deviceStorageService.createDevice(command);

        // Então
        assertNotNull(result);
        assertEquals("token123", result.getTokenDispositivo());

        verify(responsibleRepository, times(1)).findResponsibleByCpf(command.getCpfResponsavel());
        verify(deviceStorageRepository, times(1)).findByTokenDispositivo(command.getTokenDispositivo());
        verify(deviceStorageRepository, times(1)).save(existingDevice);
        verify(mapperUtil, times(1)).map(existingDevice, DeviceStorageVO.class);
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

        when(deviceStorageRepository.findByTokenDispositivo(command.getTokenDispositivo()))
                .thenReturn(Optional.of(existingDevice));

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
}
