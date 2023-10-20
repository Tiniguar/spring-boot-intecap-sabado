package com.company.intecap.apibooks.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="libro")
public class EntityLibro implements Serializable {
    private static  final  long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  nombre;

    @Column(length = 100)
    private String descripcion;

    private String autor;

    public EntityLibro() {
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
