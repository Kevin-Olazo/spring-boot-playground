package com.gadev.conversorapi.controller;

import com.gadev.conversorapi.model.ConversionRequest;
import com.gadev.conversorapi.model.ConversionResponse;
import com.gadev.conversorapi.service.DistanciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convertir/distancia")
public class DistanciaController {

    private final DistanciaService distanciaService;

    public DistanciaController(DistanciaService distanciaService) {
        this.distanciaService = distanciaService;
    }

    @PostMapping
    public ResponseEntity<ConversionResponse> convertir(@RequestBody ConversionRequest request){
        return ResponseEntity.ok(distanciaService.convertir(request));
    }
}
