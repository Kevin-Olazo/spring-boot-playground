package com.gadev.conversorapi.service;

import com.gadev.conversorapi.enums.UnidadPeso;
import com.gadev.conversorapi.exception.ConversionException;
import com.gadev.conversorapi.model.ConversionRequest;
import com.gadev.conversorapi.model.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public class PesoService {

    public ConversionResponse convertir(ConversionRequest request) {

        UnidadPeso desde = parsearUnidad(request.getDesde());
        UnidadPeso hacia = parsearUnidad(request.getHacia());
        double valor = request.getValor();

        if (valor < 0){
            throw new ConversionException("El peso no puede ser negativo");
        }

        if (desde == hacia){
            throw new ConversionException("Las unidades de origen y destino son iguales");
        }

        double enGramos = aGramos(valor, desde);
        double resultado = desdeGramos(enGramos, hacia);
        double resultadoRedondeado = Math.round(resultado * 1000.0) / 1000.0;

        String formula = desde.name() + " -> GRAMOS -> " + hacia.name();

        return new ConversionResponse(valor, desde.name(), resultadoRedondeado, hacia.name(), formula);
    }

    private double desdeGramos(double gramos, UnidadPeso unidad){
        return switch (unidad) {
            case GRAMOS -> gramos;
            case KILOGRAMOS -> gramos / 1000;
            case LIBRAS -> gramos / 453.59237;
            case ONZAS -> gramos / 28.3495231;
        };
    }

    private double aGramos(double valor, UnidadPeso unidad){
        return switch (unidad) {
            case GRAMOS -> valor;
            case KILOGRAMOS -> valor * 1000;
            case LIBRAS -> valor * 453.59237;
            case ONZAS -> valor * 28.3495231;
        };
    }

    private UnidadPeso parsearUnidad(String unidad){
        try {
            return UnidadPeso.valueOf(unidad.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unidad de peso no válida: " + unidad + ". Válidas: GRAMOS, KILOGRAMOS, LIBRAS, ONZAS");
        }

    }
}
