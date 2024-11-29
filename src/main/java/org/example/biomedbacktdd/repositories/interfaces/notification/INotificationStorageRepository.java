package org.example.biomedbacktdd.repositories.interfaces.notification;

import org.example.biomedbacktdd.entities.notification.NotificationStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface INotificationStorageRepository extends JpaRepository<NotificationStorage, Long> {
    List<NotificationStorage> findByResponsavelCpfRes(String cpfRes);
    Optional<NotificationStorage> findByIdNotificacao(int idNotificacao);
}

