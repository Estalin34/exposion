package com.example.banco.Servicios;

import com.example.banco.Entidad.Formulario;
import com.example.banco.Repositorios.FormularioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormularioServicio {

    @Autowired
    private FormularioRepositorio formularioRepositorio;

    public void guardar(Formulario formulario) {
        formularioRepositorio.save(formulario);
    }

    public Formulario obtenerUltimoFormulario() {
        List<Formulario> formularios = formularioRepositorio.findAll();
        return formularios.isEmpty() ? null : formularios.get(formularios.size() - 1); // Obtén el último formulario guardado
    }

    public Formulario obtenerPorId(Long id) {
        Optional<Formulario> formulario = formularioRepositorio.findById(id); // Obtén el formulario por ID
        return formulario.orElse(null); // Retorna el formulario si existe, o null si no
    }
}
