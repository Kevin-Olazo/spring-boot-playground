package com.gadev.proyecto01helloapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @GetMapping("/saludo")
    public String saludar(@RequestParam(defaultValue = "Mundo") String nombre){
        return "Hola " + nombre + "!";
    }

    @GetMapping("/fecha")
    public String obtenerFecha(){
        // Obtenemos la fecha y hora actual del servidor
        LocalDateTime ahora = LocalDateTime.now();
        // Definimos el formato en el que se va mostrar la fecha y hora
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // retornamos la fecha formato
        return "Fecha y hora actual: " + ahora.format(formato);
    }

    @GetMapping("/momento")
    public String saludoPorMomento(){
        // Obtenemos la hora actual (formato 24 horas, de 0 a 23)
        int hora = LocalDateTime.now().getHour();

        // Retornamos saludo dependiendo de la hora
        if (hora >= 6 && hora < 12){
            return "Buenos dias!";
        } else if (hora >= 12 && hora < 19 ){
            return "Buenas tardes!";
        } else {
            return "Buenas noches!";
        }
    }

}
