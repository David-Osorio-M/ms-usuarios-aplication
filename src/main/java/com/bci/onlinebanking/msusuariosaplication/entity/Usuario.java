package com.bci.onlinebanking.msusuariosaplication.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo", unique = true)
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id") 
    private List<Telefono> telefonos;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "token")
    private String token;

    @Column(name = "isactive")
    private Boolean isActive;

    @Entity
    @Table(name = "telefono")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Telefono {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "number")
        private String number;

        @Column(name = "citycode")
        private String citycode;

        @Column(name = "countrycode")
        private String countrycode;

    }


}