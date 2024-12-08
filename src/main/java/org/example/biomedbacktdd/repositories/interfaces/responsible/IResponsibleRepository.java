package org.example.biomedbacktdd.repositories.interfaces.responsible;

import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IResponsibleRepository extends JpaRepository<Responsible, String> {
    @Query("SELECT r FROM Responsible r WHERE r.emailRes = :emailRes")
    Optional<Responsible> findResponsibleByEmail(@Param("emailRes") String emailRes);

    @Query("SELECT r FROM Responsible r WHERE r.cpfRes = :cpfRes")
    Optional<Responsible> findResponsibleByCpf(@Param("cpfRes") String cpfRes);

    @Query("SELECT r FROM Responsible r WHERE r.contato1Res = :telefone")
    Optional<Responsible> findResponsibleByTelefone(@Param("telefone") String telefone);
}