package com.example.bakery.repositories;

import com.example.bakery.models.Baker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BakerRepository extends JpaRepository<Baker, Long> {
    @Query("SELECT con FROM Baker con  WHERE con.login=(:login)")
    Baker findByTeacherlogin(@Param("login") String login);
}