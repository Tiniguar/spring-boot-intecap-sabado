package com.intecap.ctiniguar.Articulos.services;

import com.intecap.ctiniguar.Articulos.models.Articulo;
import com.intecap.ctiniguar.Articulos.models.Fabricante;
import com.intecap.ctiniguar.Articulos.response.ArticuloResponseRest;
import com.intecap.ctiniguar.Articulos.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {
    public ResponseEntity<ArticuloResponseRest> buscaArticulo();

    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorid(Long id);

    public ResponseEntity<ArticuloResponseRest> guardarArticulo(Articulo articulo);

    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long id);

    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id);
}
