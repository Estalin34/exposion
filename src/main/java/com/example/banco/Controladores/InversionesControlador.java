package com.example.banco.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InversionesControlador {
    @GetMapping("/templates/Banco/inversiones")
    public String mostrarInversiones() {
        return "Banco/inversiones"; // El nombre del archivo sin el prefijo de la carpeta
    }
}