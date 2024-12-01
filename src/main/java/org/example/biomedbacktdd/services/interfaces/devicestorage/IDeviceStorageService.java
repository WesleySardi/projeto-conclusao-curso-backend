package org.example.biomedbacktdd.services.interfaces.devicestorage;

import org.example.biomedbacktdd.VO.auth.DeviceStorageVO;
import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;

import java.util.List;

public interface IDeviceStorageService {
    List<DeviceStorageVO> findDispositivosByCpfDep(String cpfDep);
    DeviceStorageVO createDevice(DeviceStorageCommand deviceStorageDTO);
}
