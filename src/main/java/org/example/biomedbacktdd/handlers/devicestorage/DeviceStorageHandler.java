package org.example.biomedbacktdd.handlers.devicestorage;

import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.results.DeviceStorageResult;
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
    private static final String SUCCESS = "Sucesso";

    public DeviceStorageHandler(IDeviceStorageService deviceStorageService) {
        this.deviceStorageService = deviceStorageService;
    }

    public ResponseEntity<StatusResponseViewModel<DeviceStorageResult>> handleCreate(DeviceStorageCommand deviceStorageCommand) {
        try {
            DeviceStorageResult createdDevice = deviceStorageService.createDevice(deviceStorageCommand);
            StatusResponseViewModel<DeviceStorageResult> response = new StatusResponseViewModel<>(
                    createdDevice,
                    SUCCESS,
                    "Dispositivo registrado com sucesso.",
                    HttpStatus.OK.value(),
                    true
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ServiceException se) {
            StatusResponseViewModel<DeviceStorageResult> errorResponse = new StatusResponseViewModel<>(
                    null,
                    "Erro",
                    se.getMessage(),
                    HttpStatus.CONFLICT.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            StatusResponseViewModel<DeviceStorageResult> errorResponse = new StatusResponseViewModel<>(
                    null,
                    "Erro",
                    "Erro interno do servidor.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<List<DeviceStorageResult>>> handleFindDispositivosByCpfDep(String cpfDep) {
        try {
            List<DeviceStorageResult> devices = deviceStorageService.findDispositivosByCpfDep(cpfDep);
            StatusResponseViewModel<List<DeviceStorageResult>> response = new StatusResponseViewModel<>(
                    devices,
                    SUCCESS,
                    "Dispositivos encontrados com sucesso.",
                    HttpStatus.OK.value(),
                    true
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            StatusResponseViewModel<List<DeviceStorageResult>> errorResponse = new StatusResponseViewModel<>(
                    null,
                    "Erro",
                    e.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<List<DeviceStorageResult>>> handleFindDispositivosByCpfRes(String cpfRes) {
        try {
            List<DeviceStorageResult> devices = deviceStorageService.findDispositivosByCpfRes(cpfRes);
            StatusResponseViewModel<List<DeviceStorageResult>> response = new StatusResponseViewModel<>(
                    devices,
                    SUCCESS,
                    "Dispositivos encontrados com sucesso.",
                    HttpStatus.OK.value(),
                    true
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            StatusResponseViewModel<List<DeviceStorageResult>> errorResponse = new StatusResponseViewModel<>(
                    null,
                    "Erro",
                    e.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    false
            );
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

}
