package com.gadev.conversorapi.controller;

import com.gadev.conversorapi.model.ConversionRequest;
import com.gadev.conversorapi.model.ConversionResponse;
import com.gadev.conversorapi.service.PesoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convertir/peso")
public class PesoController {

    private final PesoService pesoService;

    public PesoController(PesoService pesoService) {
        this.pesoService = pesoService;
    }

    @PostMapping
    public ResponseEntity<ConversionResponse> convertir(@RequestBody ConversionRequest request) {
        return ResponseEntity.ok(pesoService.convertir(request));
    }
}