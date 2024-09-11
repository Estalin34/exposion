package com.example.banco.Controladores;

import com.example.banco.Servicios.PdfServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PantallaPdfControlador {

    @Autowired
    private PdfServicio pdfService;

    @GetMapping("/pantalla-bien")
    public String mostrarPantallaBien(@RequestParam("idFormulario") String idFormulario, Model model) {
        model.addAttribute("idFormulario", idFormulario);
        return "Banco/pantalla_bien"; // Nombre de tu plantilla HTML
    }

}
