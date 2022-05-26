package com.example.bakery.models;

import com.example.bakery.dto.AllRoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SqlResultSetMapping(
        name = "roleMapping",
        classes = {@ConstructorResult(
                targetClass = AllRoleDto.class,
                columns = {@ColumnResult(
                        name = "login",
                        type = String.class
                ), @ColumnResult(
                        name = "password",
                        type = String.class
                ), @ColumnResult(
                        name = "type",
                        type = String.class
                )}
        )}
)
@Entity
@Table(
        name = "allRole"
)
public class AllRole {
    @Column(
            name = "type",
            length = 100
    )
    @ApiModelProperty("name")
    @NotNull
    public String type;
    @Column(
            name = "login",
            length = 100
    )
    @ApiModelProperty("login")
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
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @ApiModelProperty("account id")
    private Long id;

}