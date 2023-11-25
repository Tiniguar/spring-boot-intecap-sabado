package com.intecap.ctiniguar.Articulos.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name="roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles  implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Nombre es un campo requerido")
    @NotNull(message = "El nombre es requerido")
    @Column(length = 50)
    private String  nombre;
}
