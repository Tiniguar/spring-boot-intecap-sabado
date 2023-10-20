package com.company.intecap.apibooks.response;

import com.company.intecap.apibooks.model.EntityLibro;

import java.util.List;

public class LibroReponse {
    private List<EntityLibro> libros;

    public List<EntityLibro> getLibros() {
        return libros;
    }

    public void setLibros(List<EntityLibro> libros) {
        this.libros = libros;
    }
}
