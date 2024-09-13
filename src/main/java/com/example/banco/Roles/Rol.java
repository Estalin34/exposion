package com.example.banco.Roles;

public enum Rol {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String nombreRol;

    Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }
}
