package com.gadev.calculadoraapi.service;

import com.gadev.calculadoraapi.model.CalculadoraRequest;
import com.gadev.calculadoraapi.model.CalculadoraResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    public CalculadoraResponse calcular(CalculadoraRequest request) {

        double numero1 = request.getNumero1();
        double numero2 = request.getNumero2();
        String operacion = request.getOperacion().toLowerCase().trim();
        double resultado;

        switch (operacion) {
            case "sumar" -> resultado = numero1 + numero2;
            case "restar" -> resultado = numero1 - numero2;
            case "multiplicar" -> resultado = numero1 * numero2;
            case "dividir" -> {
                if (numero2 == 0) {
                    return new CalculadoraResponse("Error: División por cero no permitida");
                }
                resultado = numero1 / numero2;
            }
            default -> {
                return new CalculadoraResponse(
                        "Error: Operación no válida. Use 'sumar', 'restar', 'multiplicar' o 'dividir'."
                );
            }
        }

        return new CalculadoraResponse(numero1, numero2, operacion, resultado);
    }
}
