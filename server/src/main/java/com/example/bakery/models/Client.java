package com.example.bakery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import com.example.bakery.dto.ClientDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@SqlResultSetMapping(
        name = "clientMapping",
        classes = @ConstructorResult(
                targetClass = ClientDto.class,
                columns = {
                        @ColumnResult(name = "id", type=Long.class),
                        @ColumnResult(name = "username", type=String.class),
                        @ColumnResult(name ="surname", type =String.class),
                        @ColumnResult(name ="login", type =String.class),
                        @ColumnResult(name ="password", type =String.class),
                        @ColumnResult(name ="email", type =String.class),
                }
        )
)


@Entity
@Table(name = "client")
    public class Client {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")

        public Long id;

        @NotNull
        @Column(name = "name", length = 100)
        @ApiModelProperty("client name")
        public String name;

        @NotNull
        @Column(name = "surname", length = 100)
        @ApiModelProperty("client surname")
        public String surname;

        @NotNull
        @Column(name = "login", length = 100)
        @ApiModelProperty("client login")
        public String login;


        @NotNull
        @Column(name = "password", length = 100)
        @ApiModelProperty("hash of password")
        @JsonIgnore
        public String password;

        @NotNull
        @Column(name = "type", length = 100)
        @ApiModelProperty("type")
        @JsonIgnore
        public String type;

        @NotNull
        @Column(name = "email", length = 100)
        @ApiModelProperty("email")
        public String email;

        @NotNull
        @Column(name = "yourBaker", length = 100)
        @ApiModelProperty("yourBaker")
        public String yourBaker;

    }
