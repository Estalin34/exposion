package com.example.banco.Servicios;

import com.example.banco.Entidad.Transaccion;
import com.example.banco.Repositorios.TransaccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServicio {

    @Autowired
    private TransaccionRepositorio transaccionRepositorio;

    public List<Transaccion> listarTodas() {
        return transaccionRepositorio.findAll();
    }

    public Transaccion guardar(Transaccion transaccion) {
        return transaccionRepositorio.save(transaccion);
    }

    public Optional<Transaccion> obtenerPorId(Long id) {
        return transaccionRepositorio.findById(id);
    }

    public void eliminar(Long id) {
        transaccionRepositorio.deleteById(id);
    }
}
