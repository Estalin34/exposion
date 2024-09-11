package com.example.banco.Repositorios;

import com.example.banco.Entidad.Garantia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantiaRepositorio  extends JpaRepository <Garantia,Long> {
}
