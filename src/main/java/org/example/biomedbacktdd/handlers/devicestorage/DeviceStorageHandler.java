package org.example.biomedbacktdd.handlers.devicestorage;

import org.example.biomedbacktdd.VO.auth.DeviceStorageVO;
import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.exceptions.ServiceException;
import org.example.biomedbacktdd.services.interfaces.devicestorage.IDeviceStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceStorageHandler {

    private final IDeviceStorageService deviceStorageService;

    public DeviceStorageHandler(IDeviceStorageService deviceStorageService) {
        this.deviceStorageService = deviceStorageService;
    }

    public ResponseEntity<StatusResponseViewModel<DeviceStorageVO>> handleCreate(DeviceStorageCommand deviceStorageCommand) {
        try {
            DeviceStorageVO createdDevice = deviceStorageService.createDevice(deviceStorageCommand);
            StatusResponseViewModel<DeviceStorageVO> response = new StatusResponseViewModel<>(
                    createdDevice,
                    "Sucesso",
                    "Dispositivo registrado com sucesso.",
                    HttpStatus.OK.value(),
                    true
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ServiceException se) {
            StatusResponseViewModel<DeviceStorageVO> errorResponse = new StatusResponseViewModel<>(
                    null,
                    "Erro",
                    se.getMessage(),
                    HttpStatus.CONFLICT.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            StatusResponseViewModel<DeviceStorageVO> errorResponse = new StatusResponseViewModel<>(
                    null,
                    "Erro",
                    "Erro interno do servidor.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<List<DeviceStorageVO>>> handleFindDispositivosByCpfDep(String cpfDep) {
        try {
            List<DeviceStorageVO> devices = deviceStorageService.findDispositivosByCpfDep(cpfDep);
            StatusResponseViewModel<List<DeviceStorageVO>> response = new StatusResponseViewModel<>(
                    devices,
                    "Sucesso",
                    "Dispositivos encontrados com sucesso.",
                    HttpStatus.OK.value(),
                    true
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            StatusResponseViewModel<List<DeviceStorageVO>> errorResponse = new StatusResponseViewModel<>(
                    null,
                    "Erro",
                    e.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
