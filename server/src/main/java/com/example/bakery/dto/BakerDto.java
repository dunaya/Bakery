package com.example.bakery.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class BakerDto {
    @Null
    private Long id;
    @NotNull
    private String name;

    @NotNull
    private String surname;


    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String yourClient;

    @NotNull
    private String specialisation;

    @NotNull
    private Integer ordersCount;

    @NotNull
    private Integer finishedOrdersCount;

    public BakerDto(Long id, String name, String surname, String login, String password, String email, String specialisation, String yourClient, Integer ordersCount, Integer finishedOrdersCount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.specialisation = specialisation;
        this.yourClient = yourClient;
        this.ordersCount = ordersCount;
        this.finishedOrdersCount = finishedOrdersCount;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String link) {
        this.email = email;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getYourClient(){
        return yourClient;
    }

    public void setYourClient(){
        this.yourClient = yourClient;
    }
}

