package org.example.biomedbacktdd.repositories.interfaces.notification;

import org.example.biomedbacktdd.entities.notification.NotificationStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INotificationStorageRepository extends JpaRepository<NotificationStorage, Long> {
    List<NotificationStorage> findByResponsavelCpfRes(String cpfRes);
}

