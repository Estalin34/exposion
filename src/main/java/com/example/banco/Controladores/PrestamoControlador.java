    package com.example.banco.Controladores;

    import com.example.banco.Entidad.Garantia;
    import com.example.banco.Entidad.Prestamo;
    import com.example.banco.Servicios.ClienteServicio;
    import com.example.banco.Servicios.PrestamoServicio;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.propertyeditors.CustomDateEditor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.WebDataBinder;
    import org.springframework.web.bind.annotation.*;

    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Optional;

    @Controller
    public class PrestamoControlador {

        @Autowired
        private PrestamoServicio prestamoServicio;

        @Autowired
        private ClienteServicio clienteServicio;

        // Configuración del formato de fecha
        @InitBinder
        public void initBinder(WebDataBinder binder) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        }

        // Listar todos los préstamos
        @GetMapping("/prestamos")
        public String listarPrestamos(Model model) {
            model.addAttribute("prestamos", prestamoServicio.listarTodos());
            return "Banco/prestamos";
        }

        // Mostrar un préstamo específico por ID
        @GetMapping("/prestamos/{id}")
        public String obtenerPrestamo(@PathVariable Long id, Model model) {
            Optional<Prestamo> prestamo = prestamoServicio.obtenerPorId(id);
            if (prestamo.isPresent()) {
                model.addAttribute("prestamo", prestamo.get());
                model.addAttribute("clientes", clienteServicio.listarTodos());
                return "Banco/formularioPrestamo";
            }
            return "redirect:/prestamos";
        }

        // Mostrar el formulario para crear un nuevo préstamo
        @GetMapping("/prestamos/nuevo")
        public String nuevoPrestamo(Model model) {
            model.addAttribute("prestamo", new Prestamo());
            model.addAttribute("clientes", clienteServicio.listarTodos());
            return "Banco/formularioPrestamo";
        }

        @GetMapping("/prestamos/editar/{id}")
        public String editarPrestamo(@PathVariable Long id, Model model) {
            Optional<Prestamo> prestamo = prestamoServicio.obtenerPorId(id);
            if (prestamo.isPresent()) {
                model.addAttribute("prestamo", prestamo.get());
                model.addAttribute("clientes", clienteServicio.listarTodos()); // Asegúrate de que esto devuelve una lista válida
                return "Banco/formularioPrestamo";
            }
            return "redirect:/prestamos";
        }

        // Guardar un préstamo (crear o actualizar)
        @PostMapping("/prestamos")
        public String guardarPrestamo(@ModelAttribute("prestamo") Prestamo prestamo, Model model) {
            // Validar y guardar el préstamo
            if (prestamo.getCliente() == null || prestamo.getCliente().getId() == null) {
                model.addAttribute("error", "El cliente es obligatorio.");
                model.addAttribute("clientes", clienteServicio.listarTodos());
                return "Banco/formularioPrestamo";
            }


            prestamoServicio.guardar(prestamo);

            // Redirigir a la lista de préstamos después de guardar
            return "redirect:/prestamos";
        }

        // Eliminar un préstamo
        @GetMapping("/prestamos/eliminar/{id}")
        public String eliminarPrestamo(@PathVariable Long id) {
            prestamoServicio.eliminar(id);
            return "redirect:/prestamos";
        }
    }
