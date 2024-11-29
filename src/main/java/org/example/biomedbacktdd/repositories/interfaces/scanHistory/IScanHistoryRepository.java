package org.example.biomedbacktdd.repositories.interfaces.scanHistory;

import org.example.biomedbacktdd.entities.scanHistory.ScanHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScanHistoryRepository extends JpaRepository<ScanHistory, Long> {

    // Consulta personalizada para buscar scans por CPF do dependente
    List<ScanHistory> findByDepCpf(String depCpf);
}
