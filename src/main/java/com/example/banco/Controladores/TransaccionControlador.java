package com.example.banco.Controladores;

import com.example.banco.Entidad.Transaccion;
import com.example.banco.Servicios.CuentaServicio;
import com.example.banco.Servicios.TransaccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TransaccionControlador {

    @Autowired
    private TransaccionServicio transaccionServicio;

    @Autowired
    private CuentaServicio cuentaServicio;

    @GetMapping("/transacciones")
    public String listarTransacciones(Model model) {
        model.addAttribute("transacciones", transaccionServicio.listarTodas());
        return "Banco/transacciones"; // Nombre del archivo sin la extensión .html
    }

    @GetMapping("/transacciones/{id}")
    public String obtenerTransaccion(@PathVariable Long id, Model model) {
        Optional<Transaccion> transaccion = transaccionServicio.obtenerPorId(id);
        if (transaccion.isPresent()) {
            model.addAttribute("transaccion", transaccion.get());
            model.addAttribute("cuentas", cuentaServicio.listarTodas()); // Verifica que esta lista no sea null
            return "Banco/formularioTransaccion";
        }
        return "redirect:/transacciones";
    }


    @GetMapping("/transacciones/nueva")
    public String nuevaTransaccion(Model model) {
        model.addAttribute("transaccion", new Transaccion());
        model.addAttribute("cuentas", cuentaServicio.listarTodas());
        return "Banco/formularioTransaccion"; // Nombre del archivo sin la extensión .html
    }

    // Método para editar una transacción existente
    @GetMapping("/transacciones/editar/{id}")
    public String editarTransaccion(@PathVariable Long id, Model model) {
        Optional<Transaccion> transaccion = transaccionServicio.obtenerPorId(id);
        if (transaccion.isPresent()) {
            model.addAttribute("transaccion", transaccion.get());
            model.addAttribute("cuentas", cuentaServicio.listarTodas());
            return "Banco/formularioTransaccion"; // Nombre del archivo sin la extensión .html
        }
        return "redirect:/transacciones";
    }


    @PostMapping("/transacciones")
    public String guardarTransaccion(@ModelAttribute Transaccion transaccion) {
        transaccionServicio.guardar(transaccion);
        return "redirect:/transacciones";
    }

    @GetMapping("/transacciones/eliminar/{id}")
    public String eliminarTransaccion(@PathVariable Long id) {
        transaccionServicio.eliminar(id);
        return "redirect:/transacciones";
    }
}
