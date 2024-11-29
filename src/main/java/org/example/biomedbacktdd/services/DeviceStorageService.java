package org.example.biomedbacktdd.services;

import lombok.extern.slf4j.Slf4j;
import org.example.biomedbacktdd.VO.auth.DeviceStorageVO;
import org.example.biomedbacktdd.dto.commands.DeviceStorageCommand;
import org.example.biomedbacktdd.entities.devicestorage.DeviceStorage;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.exceptions.ServiceException;
import org.example.biomedbacktdd.repositories.interfaces.devicestorage.IDeviceStorageRepository;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.example.biomedbacktdd.services.interfaces.devicestorage.IDeviceStorageService;
import org.example.biomedbacktdd.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DeviceStorageService implements IDeviceStorageService {

    private final IDeviceStorageRepository deviceStorageRepository;
    private final IResponsibleRepository responsibleRepository;
    private final MapperUtil mapperUtil;

    public static final String RESPONSIBLE_NOT_FOUND = "Nenhum responsável encontrado com o CPF: ";
    public static final String DEVICE_NOT_FOUND = "Nenhum dispositivo associado ao CPF: ";

    public DeviceStorageService(IDeviceStorageRepository deviceStorageRepository,
                                IResponsibleRepository responsibleRepository,
                                MapperUtil mapperUtil) {
        this.deviceStorageRepository = deviceStorageRepository;
        this.responsibleRepository = responsibleRepository;
        this.mapperUtil = mapperUtil;
    }

    public List<DeviceStorageVO> findDispositivosByCpfDep(String cpfDep) {
        log.info("Procurando dispositivos associados ao CPF: {}", cpfDep);

        List<DeviceStorage> devices = deviceStorageRepository.findTokenDispositivosByCpfDep(cpfDep);

        if (devices.isEmpty()) {
            throw new ServiceException(DEVICE_NOT_FOUND + cpfDep);
        }

        return devices.stream()
                .map(device -> mapperUtil.map(device, DeviceStorageVO.class))
                .toList();
    }

    @Transactional
    public DeviceStorageVO createDevice(DeviceStorageCommand deviceStorageCommand) {
        if (deviceStorageCommand.getCpfResponsavel() == null) {
            throw new ServiceException("O CPF do responsável não pode ser nulo.");
        }

        Responsible responsible = responsibleRepository.findResponsibleByCpf(deviceStorageCommand.getCpfResponsavel())
                .orElseThrow(() -> new ServiceException(RESPONSIBLE_NOT_FOUND + deviceStorageCommand.getCpfResponsavel()));

        String tokenDispositivo = deviceStorageCommand.getTokenDispositivo();

        // Verifique se o token já existe
        Optional<DeviceStorage> existingDeviceOpt = deviceStorageRepository.findByTokenDispositivo(tokenDispositivo);

        if (existingDeviceOpt.isPresent()) {
            DeviceStorage existingDevice = existingDeviceOpt.get();

            // Atualize dataCadastro
            existingDevice.setDataCadastro(new Date());

            // Opcional: Verifique se o responsável é o mesmo
            if (!existingDevice.getResponsavel().getCpfRes().equals(deviceStorageCommand.getCpfResponsavel())) {
                throw new ServiceException("Este token já está associado a outro responsável.");
            }

            deviceStorageRepository.save(existingDevice);
            log.info("TokenDispositivo já existente. dataCadastro atualizado para: {}", existingDevice.getDataCadastro());

            // Mapeie para VO e retorne
            return mapperUtil.map(existingDevice, DeviceStorageVO.class);
        }

        // Se o token não existir, crie um novo
        DeviceStorage deviceStorage = new DeviceStorage();
        deviceStorage.setTokenDispositivo(tokenDispositivo);
        deviceStorage.setResponsavel(responsible);

        DeviceStorage savedDevice = deviceStorageRepository.save(deviceStorage);
        log.info("Novo dispositivo registrado com token: {}", savedDevice.getTokenDispositivo());

        return mapperUtil.map(savedDevice, DeviceStorageVO.class);
    }
}
