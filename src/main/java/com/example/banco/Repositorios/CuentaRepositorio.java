package com.example.banco.Repositorios;

import com.example.banco.Entidad.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {
}