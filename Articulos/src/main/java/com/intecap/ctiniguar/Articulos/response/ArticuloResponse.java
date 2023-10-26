package com.intecap.ctiniguar.Articulos.response;

import com.intecap.ctiniguar.Articulos.models.Articulo;

import java.util.List;

public class ArticuloResponse {
    private List<Articulo> articulos;

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
}
