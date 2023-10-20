package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.model.EntityLibro;
import com.company.intecap.apibooks.response.LibroReponse;
import com.company.intecap.apibooks.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {
    //interface para acceder a los datos de la tabla libro de la base de datos (CRUD)

    //listar todos los libros;

    public ResponseEntity<LibroResponseRest> buscarLibros();

    public ResponseEntity<LibroResponseRest> buscarLibroPorid(Long id);

    public ResponseEntity<LibroResponseRest> guardarLibro(EntityLibro libro);

    public ResponseEntity<LibroResponseRest> actualizarLibro(EntityLibro libro, Long id);

    public ResponseEntity<LibroResponseRest> eliminarLibro(Long id);
}
