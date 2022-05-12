package com.example.bakery.models;

import com.example.bakery.dto.BakerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SqlResultSetMapping(
        name = "bakerMapping",
        classes = @ConstructorResult(
                targetClass = BakerDto.class,
                columns = {
                        @ColumnResult(name = "id", type=Long.class),
                        @ColumnResult(name = "name", type=String.class),
                        @ColumnResult(name ="surname", type =String.class),
                        @ColumnResult(name ="login", type =String.class),
                        @ColumnResult(name ="password", type =String.class),
                        @ColumnResult(name ="specialisation", type =String.class)
                }

        )
)

@Entity
@Table(name = "baker")
public class Baker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty("baker id")
    public Long id;

    @NotNull
    @Column(name = "name", length = 100)
    @ApiModelProperty("baker name")
    public String name;

    @NotNull
    @Column(name = "surname", length = 100)
    @ApiModelProperty("baker surname")
    public String surname;

    @NotNull
    @Column(name = "type", length = 100)
    @ApiModelProperty("baker")
    public String type;


    @NotNull
    @Column(name = "login", length = 100)
    @ApiModelProperty("baker login")
    public String login;


    @NotNull
    @Column(name = "password", length = 100)
    @ApiModelProperty("hash of password")
    @JsonIgnore
    public String password;


    @NotNull
    @Column(name = "specialisation", length = 100)
    @ApiModelProperty("specialisation")
    public String specialisation;
}