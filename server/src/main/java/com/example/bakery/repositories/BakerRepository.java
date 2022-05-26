package com.example.bakery.repositories;

import com.example.bakery.models.Baker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface BakerRepository extends JpaRepository<Baker, Long> {
    @Query("SELECT con FROM Baker con  WHERE con.login=(:login)")
    Baker findByBakerlogin(@Param("login") String login);
    @Query("SELECT con FROM Baker con  WHERE con.yourClient=(:yourClient)")
    String findByBakerClient(@Param("yourClient") String yourClient);

    @Query("SELECT con.finishedOrdersCount, con.name, con.surname, con.login FROM Baker con  WHERE con.specialisation='cakes' ")
    List<List<String>> findSpecCakes();

    @Query("SELECT con.finishedOrdersCount, con.name, con.surname, con.login FROM Baker con  WHERE con.specialisation='cookies' ")
    List<List<String>> findSpecBiscuits();

    @Query("SELECT con.finishedOrdersCount, con.name, con.surname, con.login FROM Baker con  WHERE con.specialisation='cupcakes' ")
    List<List<String>> findSpecCupcakes();

    @Query("SELECT con.finishedOrdersCount, con.name, con.surname, con.login FROM Baker con  WHERE con.specialisation='pies' ")
    List<List<String>> findSpecPies();

    @Query("SELECT con.finishedOrdersCount, con.name, con.surname, con.login FROM Baker con  WHERE con.specialisation='other' ")
    List<List<String>> findSpecOther();
}