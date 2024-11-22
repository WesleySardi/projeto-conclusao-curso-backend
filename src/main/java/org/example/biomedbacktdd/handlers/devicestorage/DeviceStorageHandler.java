package org.example.biomedbacktdd.handlers.devicestorage;

import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.services.interfaces.devicestorage.IDeviceStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeviceStorageHandler {

    private final IDeviceStorageService deviceStorageService;

    public DeviceStorageHandler(IDeviceStorageService deviceStorageService) {
        this.deviceStorageService = deviceStorageService;
    }

    public ResponseEntity<StatusResponseViewModel> handleCreate(DeviceStorageCommand deviceStorageCommand) {
        try {
            var createdDevice = deviceStorageService.createDevice(deviceStorageCommand);
            var response = new StatusResponseViewModel<>(createdDevice, "Sucesso", "Dispositivo criado com sucesso.", HttpStatus.OK.value(), true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            var errorResponse = new StatusResponseViewModel<>(null, "Erro", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel> handleFindDispositivosByCpfDep(String cpfDep) {
        try {
            var devices = deviceStorageService.findDispositivosByCpfDep(cpfDep);
            var response = new StatusResponseViewModel<>(devices, "Sucesso", "Dispositivos encontrados com sucesso.", HttpStatus.OK.value(), true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            var errorResponse = new StatusResponseViewModel<>(null, "Erro", e.getMessage(), HttpStatus.NOT_FOUND.value(), false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
