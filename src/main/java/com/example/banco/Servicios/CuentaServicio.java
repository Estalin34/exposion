package com.example.banco.Servicios;

import com.example.banco.Entidad.Cuenta;
import com.example.banco.Repositorios.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServicio {

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    public List<Cuenta> listarTodas() {
        return cuentaRepositorio.findAll();
    }

    public Cuenta obtenerPorId(Long id) {
        return cuentaRepositorio.findById(id).orElse(null);
    }

    public Cuenta guardar(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    public void eliminar(Long id) {
        cuentaRepositorio.deleteById(id);
    }
}