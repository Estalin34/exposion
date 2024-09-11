package com.example.banco.Servicios;

import com.example.banco.Entidad.Garantia;
import com.example.banco.Repositorios.GarantiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarantiaServicio {

    @Autowired
    private GarantiaRepositorio garantiaRepositorio;

    public List<Garantia> listarTodas() {
        return garantiaRepositorio.findAll();
    }

    public Garantia guardar(Garantia garantia) {
        return garantiaRepositorio.save(garantia);
    }

    public Optional<Garantia> obtenerPorId(Long id) {
        return garantiaRepositorio.findById(id);
    }

    public void eliminar(Long id) {
        garantiaRepositorio.deleteById(id);
    }
}
