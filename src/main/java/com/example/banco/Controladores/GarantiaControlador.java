package com.example.banco.Controladores;

import com.example.banco.Entidad.Garantia;
import com.example.banco.Servicios.ClienteServicio;
import com.example.banco.Servicios.GarantiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GarantiaControlador {

    @Autowired
    private GarantiaServicio garantiaServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    // Listar todas las garantías
    @GetMapping("/garantias")
    public String listarGarantias(Model model) {
        model.addAttribute("garantias", garantiaServicio.listarTodas());
        return "Banco/garantias"; // Asegúrate de que el nombre del archivo Thymeleaf es garantias.html
    }

    // Mostrar una garantía específica por ID
    @GetMapping("/garantias/{id}")
    public String obtenerGarantia(@PathVariable Long id, Model model) {
        Optional<Garantia> garantia = garantiaServicio.obtenerPorId(id);
        if (garantia.isPresent()) {
            model.addAttribute("garantia", garantia.get());
            model.addAttribute("clientes", clienteServicio.listarTodos());
            return "Banco/formularioGarantia"; // Asegúrate de que el nombre del archivo Thymeleaf es formularioGarantia.html
        }
        return "redirect:/garantias"; // Redirige si no se encuentra la garantía
    }

    // Mostrar el formulario para crear una nueva garantía
    @GetMapping("/garantias/nueva")
    public String nuevaGarantia(Model model) {
        model.addAttribute("garantia", new Garantia());
        model.addAttribute("clientes", clienteServicio.listarTodos());
        return "Banco/formularioGarantia"; // Asegúrate de que el nombre del archivo Thymeleaf es formularioGarantia.html
    }

    // Mostrar el formulario para editar una garantía existente
    @GetMapping("/garantias/editar/{id}")
    public String editarGarantia(@PathVariable Long id, Model model) {
        Optional<Garantia> garantia = garantiaServicio.obtenerPorId(id);
        if (garantia.isPresent()) {
            model.addAttribute("garantia", garantia.get());
            model.addAttribute("clientes", clienteServicio.listarTodos());
            return "Banco/formularioGarantia"; // Asegúrate de que el nombre del archivo Thymeleaf es formularioGarantia.html
        }
        return "redirect:/garantias"; // Redirige si no se encuentra la garantía
    }

    // Guardar una garantía (nueva o editada)
    @PostMapping("/garantias")
    public String guardarGarantia(@ModelAttribute Garantia garantia) {
        garantiaServicio.guardar(garantia);
        return "redirect:/garantias";
    }

    // Eliminar una garantía
    @GetMapping("/garantias/eliminar/{id}")
    public String eliminarGarantia(@PathVariable Long id) {
        garantiaServicio.eliminar(id);
        return "redirect:/garantias";
    }
}
