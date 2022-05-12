package com.example.bakery.controllers;

import com.example.bakery.models.Client;
import com.example.bakery.repositories.ClientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("client controller")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;


    @GetMapping("/clients")
    @ApiOperation("get all client")
    public ResponseEntity<List<Client>> getClient() {
        return ResponseEntity.ok(clientRepository.findAll());
    }
    @GetMapping("/clients/{id}")
    @ApiOperation("get client by id")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        return clientRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/client_by_login/{login}")
    public Client find_client(@PathVariable("login") String login) {
        System.out.println(login);
        return clientRepository.findByClientlogin(login);
    }

}
