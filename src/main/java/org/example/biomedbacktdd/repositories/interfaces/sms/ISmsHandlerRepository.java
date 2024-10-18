package org.example.biomedbacktdd.repositories.interfaces.sms;

import org.example.biomedbacktdd.entities.sms.SmsHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ISmsHandlerRepository extends JpaRepository<SmsHandler, Integer> {
    @Transactional
    long deleteByCpfDep(String cpfDep);
}