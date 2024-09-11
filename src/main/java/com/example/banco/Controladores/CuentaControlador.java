package com.example.banco.Controladores;

import com.example.banco.Entidad.Cuenta;
import com.example.banco.Servicios.CuentaServicio;
import com.example.banco.Servicios.ClienteServicio; // Asegúrate de tener este servicio disponible
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CuentaControlador {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Autowired
    private ClienteServicio clienteServicio; // Servicio para obtener los clientes

    // Listar todas las cuentas
    @GetMapping("/cuentas")
    public String listar(Model model) {
        model.addAttribute("cuentas", cuentaServicio.listarTodas());
        return "Banco/cuentas";
    }

    // Mostrar el formulario para crear una nueva cuenta
    @GetMapping("/cuentas/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("clientes", clienteServicio.listarTodos()); // Añadir la lista de clientes al modelo
        return "Banco/formulario_cuenta";
    }

    // Guardar una cuenta (nueva o editada)
    @PostMapping("/cuentas")
    public String guardar(@ModelAttribute Cuenta cuenta) {
        cuentaServicio.guardar(cuenta);
        return "redirect:/cuentas";
    }

    // Mostrar el formulario para editar una cuenta existente
    @GetMapping("/cuentas/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Cuenta cuenta = cuentaServicio.obtenerPorId(id);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            model.addAttribute("clientes", clienteServicio.listarTodos()); // Añadir la lista de clientes al modelo
            return "Banco/formulario_cuenta";
        }
        return "redirect:/cuentas";
    }

    // Eliminar una cuenta
    @GetMapping("/cuentas/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        cuentaServicio.eliminar(id);
        return "redirect:/cuentas";
    }
}
