package com.company.intecap.apibooks.controller;

import com.company.intecap.apibooks.model.EntityLibro;
import com.company.intecap.apibooks.response.LibroResponseRest;
import com.company.intecap.apibooks.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LibroRestController {
    @Autowired
    private ILibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> getLibros(){ //localhost:8080/api/v1/libros
        return libroService.buscarLibros(); //retorna respuesta al cliente
    }


    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> consultaporId(@PathVariable Long id){ //localhost:8080/api/v1/libros
        return libroService.buscarLibroPorid(id); //retorna respuesta al cliente
    }
     @PostMapping("/libros")
     public ResponseEntity<LibroResponseRest> guardarLibro(@RequestBody EntityLibro request){ //localhost:8080/api/v1/libros
         return libroService.guardarLibro(request); //retorna respuesta al cliente
     }

    @PutMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> actualizarLibro(@PathVariable Long id, @RequestBody EntityLibro request){ //localhost:8080/api/v1/libros
        return libroService.actualizarLibro (request, id); //retorna respuesta al cliente
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> elimnarLibro(@PathVariable Long id){ //localhost:8080/api/v1/libros
        return libroService.eliminarLibro ( id); //retorna respuesta al cliente
    }

}
