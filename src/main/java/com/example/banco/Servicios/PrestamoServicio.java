package com.example.banco.Servicios;

import com.example.banco.Entidad.Prestamo;
import com.example.banco.Repositorios.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServicio {

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    public List<Prestamo> listarTodos() {
        return prestamoRepositorio.findAll();
    }

    public Optional<Prestamo> obtenerPorId(Long id) {
        return prestamoRepositorio.findById(id);
    }

    public void guardar(Prestamo prestamo) {
        prestamoRepositorio.save(prestamo);
    }

    public void eliminar(Long id) {
        prestamoRepositorio.deleteById(id);
    }
}
