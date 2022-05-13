package com.example.bakery.repositories;

import com.example.bakery.models.Baker;
import com.example.bakery.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BakerRepository extends JpaRepository<Baker, Long> {
    @Query("SELECT con FROM Baker con  WHERE con.login=(:login)")
    Baker findByBakerlogin(@Param("login") String login);
    @Query("SELECT con FROM Baker con  WHERE con.yourClient=(:yourClient)")
    List<Baker> findByBakerClient(@Param("yourClient") String yourClient);
}