package com.company.intecap.apibooks.response;

import com.company.intecap.apibooks.model.EntityCategoria;

import java.util.List;

public class CategoriaResponse {
    private List<EntityCategoria> categorias;

    public List<EntityCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<EntityCategoria> categorias) {
        this.categorias = categorias;
    }
}
