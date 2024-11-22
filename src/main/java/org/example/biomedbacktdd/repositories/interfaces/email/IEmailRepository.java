package org.example.biomedbacktdd.repositories.interfaces.email;

import org.example.biomedbacktdd.entities.email.EmailHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IEmailRepository extends JpaRepository<EmailHandler, Integer> {
    boolean existsByEmailUser(String emailUser);
    Optional<EmailHandler> findByEmailUserAndEmailCode(String emailUser, int emailCode);
    @Transactional
    void deleteByEmailUser(String emailUser);
}
