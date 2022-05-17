package com.example.bakery.controllers;

import com.example.bakery.models.Baker;
import com.example.bakery.repositories.BakerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Api("baker controller")
public class BakerController {
    final
    BakerRepository bakerRepository;

    public BakerController(BakerRepository bakerRepository) {
        this.bakerRepository = bakerRepository;
    }


    @GetMapping("/bakers")
    @ApiOperation("get all bakers")
    public ResponseEntity<List<Baker>> getBaker() {
        return ResponseEntity.ok(bakerRepository.findAll());
    }

    @GetMapping("/bakers/{id}")
    @ApiOperation("get baker by id")
    public ResponseEntity<Baker> getBakerById(@PathVariable("id") Long id) {

        return bakerRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
