package com.bci.onlinebanking.msusuariosaplication.dto;

import lombok.Data;

import java.util.List;

import com.bci.onlinebanking.msusuariosaplication.entity.Usuario.Telefono;

@Data
public class UsuarioDto {

    private String nombre;
    private String correo;
    private String contrasena;
    private List<Telefono> telefonos;

}
