package com.bci.onlinebanking.msusuariosaplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bci.onlinebanking.msusuariosaplication.dto.UsuarioDto;
import com.bci.onlinebanking.msusuariosaplication.entity.Usuario;
import com.bci.onlinebanking.msusuariosaplication.exception.UsuarioAlreadyExistsException;
import com.bci.onlinebanking.msusuariosaplication.exception.ValidationException;
import com.bci.onlinebanking.msusuariosaplication.repository.UsuarioRepository;
import com.bci.onlinebanking.msusuariosaplication.service.UsuarioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioDto usuarioDto;

    @BeforeEach
    void setUp() {
        usuarioDto = new UsuarioDto();
        usuarioDto.setNombre("Test User");
        usuarioDto.setCorreo("test@example.com");
        usuarioDto.setContrasena("Test@1234");
    
       
        usuarioService = new UsuarioServiceImpl(usuarioRepository); 
        usuarioService.setEmailRegex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        usuarioService.setPasswordRegex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
        usuarioService.setJwtSecret("SecretoMuySecreto");
        usuarioService.setJwtExpirationInMillis(86400000); 
    }

    @Test
    void crearUsuario_CuandoNoExiste_DeberiaRetornarUsuarioConToken() {
        when(usuarioRepository.findByCorreo(anyString())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario result = usuarioService.crearUsuario(usuarioDto);

        assertNotNull(result);
        assertNotNull(result.getToken()); 
        assertEquals(usuarioDto.getNombre(), result.getNombre());
        assertEquals(usuarioDto.getCorreo(), result.getCorreo());
    }

    @Test
    void crearUsuario_CuandoCorreoYaExiste_DeberiaLanzarExcepcion() {
        when(usuarioRepository.findByCorreo(anyString())).thenReturn(Optional.of(new Usuario()));

        assertThrows(UsuarioAlreadyExistsException.class, () -> {
            usuarioService.crearUsuario(usuarioDto);
        });
    }

    @Test
    void crearUsuario_ConFormatoCorreoInvalido_DeberiaLanzarExcepcion() {
        usuarioDto.setCorreo("correoInvalido");

        assertThrows(ValidationException.class, () -> {
            usuarioService.crearUsuario(usuarioDto);
        });
    }

    @Test
    void crearUsuario_ConFormatoContrasenaInvalido_DeberiaLanzarExcepcion() {
        usuarioDto.setContrasena("123");

        assertThrows(ValidationException.class, () -> {
            usuarioService.crearUsuario(usuarioDto);
        });
    }
}