package org.example.biomedbacktdd.repositories.interfaces.responsible;

import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResponsibleRepository extends JpaRepository<Responsible, String> {
    @Query("SELECT r FROM Responsible r WHERE r.nomeRes LIKE LOWER(CONCAT ('%',:nomeRes,'%'))")
    Page<Responsible> findResponsiblesByName(@Param("nomeRes") String nomeRes, Pageable pageable);

    @Query("SELECT r.contato1Res FROM Responsible r INNER JOIN Dependent d ON r.cpfRes = d.cpfResDep WHERE d.cpfDep = :cpfDep")
    String findResponsibleEmergPhoneByCpfDep(@Param("cpfDep") String cpfDep);

    @Query("SELECT r.cpfRes, r.nomeRes, r.contato1Res FROM Responsible r WHERE r.emailRes = :emailRes AND r.senhaRes = :senhaRes")
    List<Object[]> findResponsiblesCpfAndName(@Param("emailRes") String emailRes, @Param("senhaRes") String senhaRes);
}