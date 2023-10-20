package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.model.EntityCategoria;
import com.company.intecap.apibooks.model.EntityLibro;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
import com.company.intecap.apibooks.response.LibroReponse;
import com.company.intecap.apibooks.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    //interface para acceder a los datos de la tabla libro de la base de datos (CRUD)

    //listar todos los libros;

    public ResponseEntity<CategoriaResponseRest> buscarCategorias();

    public ResponseEntity<CategoriaResponseRest> buscarCategoriaPorid(Long id);

    public ResponseEntity<CategoriaResponseRest> guardarCategoria(EntityCategoria categoria);

    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(EntityCategoria categoria, Long id);

    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id);
}
