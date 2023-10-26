package com.intecap.ctiniguar.Articulos.response;

import com.intecap.ctiniguar.Articulos.models.Fabricante;

import java.util.List;

public class FabricanteResponse {
    private List<Fabricante> fabricantes;

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
}
