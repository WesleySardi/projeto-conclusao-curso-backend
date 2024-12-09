package org.example.biomedbacktdd.services.interfaces.devicestorage;

import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.dto.results.DeviceStorageResult;

import java.util.List;

public interface IDeviceStorageService {
    List<DeviceStorageResult> findDispositivosByCpfDep(String cpfDep);
    List<DeviceStorageResult> findDispositivosByCpfRes(String cpfRes);
    DeviceStorageResult createDevice(DeviceStorageCommand deviceStorageDTO);
}
