package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.entities.notification.NotificationStorage;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.exceptions.ServiceException;
import org.example.biomedbacktdd.repositories.interfaces.notification.INotificationStorageRepository;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.example.biomedbacktdd.services.interfaces.notification.INotificationStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class NotificationStorageService implements INotificationStorageService {

    private final INotificationStorageRepository notificationStorageRepository;
    private final IResponsibleRepository responsibleRepository;

    @Autowired
    public NotificationStorageService(INotificationStorageRepository notificationStorageRepository,
                                      IResponsibleRepository responsibleRepository) {
        this.notificationStorageRepository = notificationStorageRepository;
        this.responsibleRepository = responsibleRepository;
    }

    public NotificationStorage storeNotification(NotificationStorageCommand notificationDTO) {
        if (notificationDTO.getDataEnvio() == null) {
            notificationDTO.setDataEnvio(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        }

        Responsible responsible = responsibleRepository.findResponsibleByCpf(notificationDTO.getCpfResponsavel())
                .orElseThrow(() -> new ServiceException("Responsável não encontrado com CPF: " + notificationDTO.getCpfResponsavel()));

        NotificationStorage notification = new NotificationStorage();
        notification.setTitulo(notificationDTO.getTitulo());
        notification.setMensagem(notificationDTO.getMensagem());
        notification.setDataEnvio(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        notification.setLida(false);
        notification.setResponsavel(responsible);

        // Salvar a notificação
        return notificationStorageRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        NotificationStorage notification = notificationStorageRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Notificação não encontrada com ID: " + id));

        notificationStorageRepository.delete(notification);
    }

    public List<NotificationStorage> getNotificationsByResponsavel(String cpfResponsavel) {
        return notificationStorageRepository.findByResponsavelCpfRes(cpfResponsavel);
    }

}
