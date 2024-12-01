package org.example.biomedbacktdd.repositories.interfaces.devicestorage;

import org.example.biomedbacktdd.entities.devicestorage.DeviceStorage;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDeviceStorageRepository extends JpaRepository<DeviceStorage, Integer> {

    @Query("SELECT d,r FROM DeviceStorage d " +
            "JOIN d.responsavel r " +
            "JOIN Dependent dep ON dep.cpfResDep = r.cpfRes " +
            "WHERE dep.cpfDep = :cpfDep")
    List<Object[]> findTokenDispositivosByCpfDep(String cpfDep);

    List<DeviceStorage> findByResponsavel(Responsible responsavel);

    @Query("SELECT ds, r FROM Responsible r INNER JOIN DeviceStorage ds ON r.cpfRes = ds.responsavel.cpfRes WHERE ds.tokenDispositivo = :tokenDispositivo")
    List<Object[]> findByTokenDispositivo(String tokenDispositivo);

}

