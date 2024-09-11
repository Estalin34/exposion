package com.example.banco.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformacionControlador {

    // Mostrar la página de información
    @GetMapping("/informacion")
    public String showInformacion() {
        return "Banco/informacion"; // Asegúrate de que el nombre del archivo Thymeleaf es informacion.html
    }
}
