package com.example.bakery.repositories;

import com.example.bakery.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT con FROM Client con  WHERE con.login=(:login)")
    Client findByClientlogin(@Param("login") String login);
    @Query("SELECT con FROM Client con  WHERE con.yourBaker=(:yourBaker)")
    List<Client> findByClientBaker(@Param("yourBaker") String yourBaker);
    @Query("SELECT con FROM Client con  WHERE con.yourBaker='default'")
    List<Client> findFreeClient();

}
