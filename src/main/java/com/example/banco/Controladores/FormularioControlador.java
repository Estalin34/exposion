package com.example.banco.Controladores;

import com.example.banco.Entidad.Formulario;
import com.example.banco.Servicios.FormularioServicio;
import com.example.banco.Servicios.PdfServicio;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class FormularioControlador {

    @Autowired
    private FormularioServicio formularioServicio;

    @Autowired
    private PdfServicio pdfServicio;

    @GetMapping("/formularios")
    public String mostrarFormulario(Model model) {
        model.addAttribute("formulario", new Formulario());
        return "Banco/formulario";
    }

    @PostMapping("/formularios")
    public String procesarFormulario(@ModelAttribute Formulario formulario, Model model) {
        formularioServicio.guardar(formulario);
        return "redirect:/confirmacion?idFormulario=" + formulario.getId();
    }

    @GetMapping("/confirmacion")
    public String mostrarConfirmacion(@RequestParam Long idFormulario, Model model) {
        model.addAttribute("idFormulario", idFormulario);
        return "Banco/pantalla";
    }

    }

