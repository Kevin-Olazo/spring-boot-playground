package com.gadev.calculadoraapi.controller;

import com.gadev.calculadoraapi.model.CalculadoraRequest;
import com.gadev.calculadoraapi.model.CalculadoraResponse;
import com.gadev.calculadoraapi.service.CalculadoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {

    // Inyección de dependencia del servicio de calculadora
    // El controlador depende del servicio para realizar las operaciones de cálculo,
    private final CalculadoraService calculadoraService;

    // Constructor para inyectar el servicio de calculadora
    public CalculadoraController(CalculadoraService calculadoraService){
        this.calculadoraService = calculadoraService;
    }

    // Ahora es POST en lugar de GET porque estamos enviando datos en el cuerpo de la solicitud
    @PostMapping("/calcular")
    // ResponseEntity te da control total sobre la respuesta HTTP
    public ResponseEntity<CalculadoraResponse> calcular(@RequestBody CalculadoraRequest request){ // Le dice a Spring que convierta el JSON del body en un objeto `CalculadoraRequest`.
        // El controlador delega la lógica de cálculo al servicio y devuelve la respuesta adecuada
        CalculadoraResponse response = calculadoraService.calcular(request);

        // Si el mensaje de la respuesta indica un error, devuelve un estado HTTP 400 (Bad Request) con el mensaje de error.
        if (response.getMensaje().startsWith("Error")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Si no hay errores, devuelve un estado HTTP 200 (OK) con la respuesta de cálculo.
        return ResponseEntity.ok(response);
    }
}
