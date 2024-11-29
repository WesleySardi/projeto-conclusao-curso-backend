package org.example.biomedbacktdd.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;
import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.entities.devicestorage.DeviceStorage;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.exceptions.ServiceException;
import org.example.biomedbacktdd.repositories.interfaces.devicestorage.IDeviceStorageRepository;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NotificationFacadeService implements INotificationFacadeService {

    private final NotificationRequestService notificationRequestService;
    private final NotificationStorageService notificationStorageService;
    private final IResponsibleRepository responsibleRepository;
    private final IDeviceStorageRepository deviceStorageRepository;

    @Autowired
    public NotificationFacadeService(NotificationRequestService notificationRequestService,
                                     NotificationStorageService notificationStorageService,
                                     IResponsibleRepository responsibleRepository,
                                     IDeviceStorageRepository deviceStorageRepository) {
        this.notificationRequestService = notificationRequestService;
        this.notificationStorageService = notificationStorageService;
        this.responsibleRepository = responsibleRepository;
        this.deviceStorageRepository = deviceStorageRepository;
    }

    public void sendAndStoreNotification(NotificationRequestCommand notificationRequestCommand) {
        // Verificar se o responsável existe
        Responsible responsible = responsibleRepository.findById(notificationRequestCommand.getCpfResponsavel())
                .orElseThrow(() -> new ServiceException("Responsável não encontrado com CPF: " + notificationRequestCommand.getCpfResponsavel()));


        // Obter os dispositivos do responsável
        List<DeviceStorage> devices = deviceStorageRepository.findByResponsavel(responsible);

        if (devices.isEmpty()) {
            throw new ServiceException("Nenhum dispositivo encontrado para o responsável com CPF: " + notificationRequestCommand.getCpfResponsavel());
        }

        // Enviar a notificação para cada dispositivo
        for (DeviceStorage device : devices) {
            try {
                NotificationRequest notificationRequest = new NotificationRequest();
                notificationRequest.setTitle(notificationRequestCommand.getTitle());
                notificationRequest.setBody(notificationRequestCommand.getBody());
                notificationRequest.setToken(device.getTokenDispositivo());

                notificationRequestService.sendNotification(notificationRequest);
            } catch (FirebaseMessagingException e) {
                // Logar o erro e continuar com o próximo dispositivo
                log.error("Erro ao enviar notificação para o dispositivo {}: {}", device.getId(), e.getMessage());
            }
        }

        // Armazenar a notificação
        NotificationStorageCommand notificationStorageCommand = new NotificationStorageCommand();
        notificationStorageCommand.setTitulo(notificationRequestCommand.getTitle());
        notificationStorageCommand.setMensagem(notificationRequestCommand.getBody());
        notificationStorageCommand.setCpfResponsavel(notificationRequestCommand.getCpfResponsavel());
        notificationStorageCommand.setCpfDependente(notificationRequestCommand.getCpfDependente());

        notificationStorageService.storeNotification(notificationStorageCommand);
    }
}
