package com.bci.onlinebanking.msusuariosaplication.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bci.onlinebanking.msusuariosaplication.dto.UsuarioDto;
import com.bci.onlinebanking.msusuariosaplication.entity.Usuario;
import com.bci.onlinebanking.msusuariosaplication.exception.UsuarioAlreadyExistsException;
import com.bci.onlinebanking.msusuariosaplication.exception.ValidationException;
import com.bci.onlinebanking.msusuariosaplication.repository.UsuarioRepository;

import jakarta.transaction.*;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Value("${app.user.email.regex}")
    private String emailRegex;

    @Value("${app.user.password.regex}")
    private String passwordRegex;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMillis;

    private String generarTokenJWT(Usuario usuario) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + jwtExpirationInMillis);

        return JWT.create()
                .withSubject(usuario.getCorreo())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Usuario crearUsuario(UsuarioDto usuarioDto) {
        if (!Pattern.matches(emailRegex, usuarioDto.getCorreo())) {
            throw new ValidationException("El formato del correo no es válido.");
        }

        if (!Pattern.matches(passwordRegex, usuarioDto.getContrasena())) {
            throw new ValidationException("El formato de la contraseña no es válido.");
        }
        usuarioRepository.findByCorreo(usuarioDto.getCorreo())
            .ifPresent(u -> {
                throw new UsuarioAlreadyExistsException("El correo ya registrado.");
            });

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setTelefonos(usuarioDto.getTelefonos());
        usuario.setCreated(new Date());
        usuario.setModified(new Date());
        usuario.setLastLogin(new Date());
        usuario.setIsActive(true);
        usuario.setToken(generarTokenJWT(usuario));

        return usuarioRepository.save(usuario);
    }

    public void setEmailRegex(String emailRegex) {
        this.emailRegex = emailRegex;
    }

    public void setPasswordRegex(String passwordRegex) {
        this.passwordRegex = passwordRegex;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public void setJwtExpirationInMillis(long jwtExpirationInMillis) {
        this.jwtExpirationInMillis = jwtExpirationInMillis;
    }

    
}