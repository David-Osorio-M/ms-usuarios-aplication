package com.bci.onlinebanking.msusuariosaplication.service;



import com.bci.onlinebanking.msusuariosaplication.dto.UsuarioDto;
import com.bci.onlinebanking.msusuariosaplication.entity.Usuario;

public interface UsuarioService {

    Usuario crearUsuario(UsuarioDto usuarioDto);
   

}
