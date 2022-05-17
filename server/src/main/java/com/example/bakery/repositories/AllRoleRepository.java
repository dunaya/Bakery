package com.example.bakery.repositories;

import com.example.bakery.models.AllRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AllRoleRepository extends JpaRepository<AllRole, Long> {
    @Query("SELECT con FROM AllRole con  WHERE con.login=(:login)")
    AllRole findByUserlogin(@Param("login") String login);
}
