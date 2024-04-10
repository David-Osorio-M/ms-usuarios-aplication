package com.bci.onlinebanking.msusuariosaplication.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bci.onlinebanking.msusuariosaplication.dto.UsuarioDto;
import com.bci.onlinebanking.msusuariosaplication.entity.Usuario;
import com.bci.onlinebanking.msusuariosaplication.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioCreado = usuarioService.crearUsuario(usuarioDto);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }


}