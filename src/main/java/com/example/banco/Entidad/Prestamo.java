package com.example.banco.Entidad;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


@Data
@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Double monto;
    private Double interes;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
