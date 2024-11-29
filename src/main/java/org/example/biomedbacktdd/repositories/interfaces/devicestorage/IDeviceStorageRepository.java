package org.example.biomedbacktdd.repositories.interfaces.devicestorage;

import org.example.biomedbacktdd.entities.devicestorage.DeviceStorage;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDeviceStorageRepository extends JpaRepository<DeviceStorage, Integer> {

    @Query("SELECT d FROM DeviceStorage d " +
            "JOIN d.responsavel r " +
            "JOIN Dependent dep ON dep.cpfResDep = r.cpfRes " +
            "WHERE dep.cpfDep = :cpfDep")
    List<DeviceStorage> findTokenDispositivosByCpfDep(String cpfDep);

    List<DeviceStorage> findByResponsavel(Responsible responsavel);

    // Adicione este método se ainda não existir
    Optional<DeviceStorage> findByTokenDispositivo(String tokenDispositivo);

}

