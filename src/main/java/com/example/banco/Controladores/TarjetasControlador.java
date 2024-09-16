package com.example.banco.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TarjetasControlador {

    @GetMapping("/tarjetas")
    public String mostrarTarjetas() {
        return "Banco/tarjetas"; // Asegúrate de que la página esté en "src/main/resources/templates/Banco/tarjetas.html"
    }


}
