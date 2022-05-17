package com.example.bakery.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class AdminDto {
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
    private String link;
    @NotNull
    private String position;

    public AdminDto(Long id, String name, String surname, String login, String password, String link, String position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.link = link;
        this.position = position;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
