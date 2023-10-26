package com.intecap.ctiniguar.Articulos.services;

import com.intecap.ctiniguar.Articulos.models.Fabricante;
import com.intecap.ctiniguar.Articulos.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IFabricanteService {
    public ResponseEntity<FabricanteResponseRest> buscarFabricantes();

    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorid(Long id);

    public ResponseEntity<FabricanteResponseRest> guardarFabricante(Fabricante fabricante);

    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long id);

    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id);
}
