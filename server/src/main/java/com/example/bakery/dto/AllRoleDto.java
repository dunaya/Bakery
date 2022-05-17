package com.example.bakery.dto;

        import javax.validation.constraints.NotNull;
        import javax.validation.constraints.Null;

public class AllRoleDto {
    @Null
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String login;
    @NotNull
    private String password;

    public AllRoleDto(Long id, String name, String type, String login, String password) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.login = login;
        this.password = password;
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

}
