package com.example.bakery.models;

import com.example.bakery.dto.AdminDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@SqlResultSetMapping(
        name = "adminMapping",
        classes = {@ConstructorResult(
                targetClass = AdminDto.class,
                columns = {@ColumnResult(
                        name = "id",
                        type = Long.class
                ), @ColumnResult(
                        name = "name",
                        type = String.class
                ), @ColumnResult(
                        name = "surname",
                        type = String.class
                ), @ColumnResult(
                        name = "login",
                        type = String.class
                ), @ColumnResult(
                        name = "password",
                        type = String.class
                ), @ColumnResult(
                        name = "link",
                        type = String.class
                ), @ColumnResult(
                        name = "position",
                        type = String.class
                )}
        )}
)
@Entity
@Table(
        name = "admin"
)
public class Admin {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "id"
    )
    @ApiModelProperty("admin id")
    public Long id;
    @Column(
            name = "name",
            length = 100
    )
    @ApiModelProperty("admin name")
    @NotNull
    public String name;
    @Column(
            name = "type",
            length = 100
    )
    @ApiModelProperty("admin name")
    @NotNull
    public String type;

    @Column(
            name = "surname",
            length = 100
    )
    @ApiModelProperty("admin surname")
    @NotNull
    public String surname;

    @Column(
            name = "login",
            length = 100
    )
    @ApiModelProperty("admin login")
    @NotNull
    public String login;

    @Column(
            name = "password",
            length = 100
    )
    @ApiModelProperty("hash of password")
    @JsonIgnore
    @NotNull
    public String password;

    @Column(
            name = "link",
            length = 100
    )
    @ApiModelProperty("admin link")
    @NotNull
    public String link;

    @Column(
            name = "position",
            length = 100
    )
    @ApiModelProperty("position")
    @NotNull
    public String position;

}
