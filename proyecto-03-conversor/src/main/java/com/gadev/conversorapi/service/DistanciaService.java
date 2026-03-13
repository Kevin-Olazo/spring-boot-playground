package com.gadev.conversorapi.service;

import com.gadev.conversorapi.enums.UnidadDistancia;
import com.gadev.conversorapi.exception.ConversionException;
import com.gadev.conversorapi.model.ConversionRequest;
import com.gadev.conversorapi.model.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public class DistanciaService {

    public ConversionResponse convertir(ConversionRequest request) {

        UnidadDistancia desde = parsearUnidad(request.getDesde());
        UnidadDistancia hacia = parsearUnidad(request.getHacia());
        double valor = request.getValor();

        if (valor < 0){
            throw new ConversionException("La distancia no puede ser negativa");
        }

        if (desde == hacia) {
            throw new ConversionException("Las unidades de origen y destino son iguales");
        }

        // Convertir todo a metros primero como unidad base
        double enMetros = aMetros(valor,desde);
        double resultado = desdeMetros(enMetros, hacia);
        double resultadoRedondeado = Math.round(resultado * 1000.0) / 1000.0;

        String formula = desde.name() + " -> METROS -> " + hacia.name();

        return new ConversionResponse(valor, desde.name(), resultadoRedondeado, hacia.name(), formula);

    }

    private double aMetros(double valor, UnidadDistancia unidad){
        return switch (unidad) {
            case METROS -> valor;
            case KILOMETROS -> valor * 1000;
            case MILLAS -> valor * 1609.344;
            case PIES -> valor * 0.3048;
        };
    }

    private double desdeMetros(double metros, UnidadDistancia unidad){
        return switch (unidad){
            case METROS -> metros;
            case KILOMETROS -> metros / 1000;
            case MILLAS -> metros / 1609.344;
            case PIES -> metros / 0.3048;
        };
    }

    private UnidadDistancia parsearUnidad(String unidad) {
        try {
            return UnidadDistancia.valueOf(unidad.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new ConversionException(
                    "Unidad de distancia no validad: '" + unidad + "'. Validad: KILOMETROS, MILLAS, METROS, PIES"
            );
        }
    }
}
