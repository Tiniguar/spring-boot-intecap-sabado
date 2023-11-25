package com.intecap.ctiniguar.Articulos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity(name="fabricante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fabricante implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Nombre es un campo requerido")
    @NotNull(message = "El nombre es requerido")
    @Column(length = 45)
    private String  nombre;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "fabricante"
    )
    @JsonIgnore
    private Set<Articulo> articulos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }
}
