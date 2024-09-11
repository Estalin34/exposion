package com.example.banco.Controladores;

import com.example.banco.Exceptions.MiException;
import com.example.banco.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Método para mostrar la página de registro
    @GetMapping("/registrar")
    public String registrar() {
        return "Usuarios/registro";  // Asegúrate de que esta ruta y el archivo registro.html existan en /templates/Usuarios/
    }

    // Método para procesar el registro de usuario
    @PostMapping("/registro")
    public String registroUsuario(@RequestParam String nombre,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  Model model) {
        try {
            usuarioServicio.registrar(nombre, email, password);
            model.addAttribute("exito", "Usuario registrado exitosamente");
            return "redirect:/";  // Redirige a la página principal (index.html)
        } catch (MiException e) {
            model.addAttribute("error", e.getMessage());
            return "Usuarios/registro";  // Regresa al formulario de registro si hay un error
        }
    }

    // Método para mostrar la página de login
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña inválidos");
        }
        return "Usuarios/login";  // Asegúrate de que esta ruta y el archivo login.html existan en /templates/Usuarios/
    }

    // Método para manejar el cierre de sesión
    @GetMapping("/logout")
    public String cerrarSesion() {
        return "redirect:/login";  // Redirige a la página de login después de cerrar sesión
    }
}
