package org.example.biomedbacktdd.repositories.interfaces.auth;

import org.example.biomedbacktdd.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    User findByUsername(@Param("userName") String userName);
}
