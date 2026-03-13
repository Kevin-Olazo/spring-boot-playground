package com.gadev.conversorapi.service;

import com.gadev.conversorapi.enums.UnidadTemperatura;
import com.gadev.conversorapi.exception.ConversionException;
import com.gadev.conversorapi.model.ConversionRequest;
import com.gadev.conversorapi.model.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public class TemperaturaService {


    public ConversionResponse convertir(ConversionRequest request) {

        UnidadTemperatura desde = parsearUnidad(request.getDesde());
        UnidadTemperatura hacia = parsearUnidad(request.getHacia());
        double valor = request.getValor();
        double resultado;
        String formula;

        if (desde == hacia) {
            throw new ConversionException("Las unidades de origen y destino no pueden ser iguales.");
        }

        if (desde == UnidadTemperatura.CELSIUS && hacia == UnidadTemperatura.FAHRENHEIT) {
            resultado = (valor * 9.0 / 5.0) + 32;
            formula = "°C × 9/5 + 32";
        } else if (desde == UnidadTemperatura.FAHRENHEIT && hacia == UnidadTemperatura.CELSIUS) {
            resultado = (valor - 32) * 5.0 / 9.0;
            formula = "(°F - 32) × 5/9";
        } else if (desde == UnidadTemperatura.CELSIUS && hacia == UnidadTemperatura.KELVIN) {
            resultado = valor + 273.15;
            formula = "°C + 273.15";
        } else if (desde == UnidadTemperatura.KELVIN && hacia == UnidadTemperatura.CELSIUS) {
            resultado = valor - 273.15;
            formula = "K - 273.15";
        } else if (desde == UnidadTemperatura.FAHRENHEIT && hacia == UnidadTemperatura.KELVIN) {
            resultado = (valor - 32) * 5.0 / 9.0 + 273.15;
            formula = "(°F - 32) × 5/9 + 273.15";
        } else {
            resultado = (valor - 273.15) * 9.0 / 5.0 + 32;
            formula = "(K - 273.15) × 9/5 + 32";
        }

        double resultadoRedondeado = Math.round(resultado * 100.0) / 100.0;

        return new ConversionResponse(
                valor,
                desde.name(),
                resultadoRedondeado,
                hacia.name(),
                formula
        );
    }

    private UnidadTemperatura parsearUnidad(String unidad) {
        try {
            return UnidadTemperatura.valueOf(unidad.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new ConversionException(
                    "Unidad de temperatura no válida: " + unidad + ". Unidades válidas: CELSIUS, FAHRENHEIT, KELVIN"
            );
        }
    }
}
