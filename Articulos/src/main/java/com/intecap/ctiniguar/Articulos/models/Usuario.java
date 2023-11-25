package com.intecap.ctiniguar.Articulos.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "NombreUsuario es un campo requerido")
    @NotNull(message = "El nombreUsuario es requerido")
    @Column(unique = true, length = 200)
    private String  nombreUsuario;

    @NotBlank(message = "Password es un campo requerido")
    @NotNull(message = "El Password es requerido")
    @Column(unique = true, length = 200)
    private String  password;

    @NotBlank(message = "habilitado es un campo requerido")
    @NotNull(message = "El campo habilitado es requerido")
    private Boolean habilitado;



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Roles> roles;

}
