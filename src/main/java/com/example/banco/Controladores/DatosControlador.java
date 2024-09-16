package com.example.banco.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatosControlador {

    @GetMapping("/masinformacion")
    public String mostrarDatos() {
        return "Usuarios/masinformacion"; // Verifica que el archivo está en "src/main/resources/templates/Usuarios/masinformacion.html"
    }
    @GetMapping("/detalles")
    public String mostrarPrestamos() {
        return "Usuarios/detalles"; // Verifica que el archivo está en "src/main/resources/templates/Usuarios/masinformacion.html"
    }
}
