package com.intecap.ctiniguar.Articulos.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "articulo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Articulo {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Nombre es un campo requerido")
    @NotNull(message = "El nombre es requerido")
    @Column(length = 45)
    private String  nombre;

    @NotBlank(message = "Precio es un campo requerido")
    @NotNull(message = "El precio es requerido")
    private BigDecimal precio;

    @NotBlank(message = "fabricante_id es un campo requerido")
    @NotNull(message = "El fabricante_id es requerido")
    @ManyToOne
    @JoinColumn(name="fabricante_id",nullable = false)
    private Fabricante fabricante;

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}
