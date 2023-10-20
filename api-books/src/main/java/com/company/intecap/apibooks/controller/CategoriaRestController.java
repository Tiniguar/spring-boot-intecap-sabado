package com.company.intecap.apibooks.controller;

import com.company.intecap.apibooks.model.EntityCategoria;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
import com.company.intecap.apibooks.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {
    //@Autowired
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> getCategorias(){ //localhost:8080/api/v1/libros
        return categoriaService.buscarCategorias(); //retorna respuesta al cliente
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> getCategoriaPorId(@PathVariable Long id){ //localhost:8080/api/v1/libros
        return categoriaService.buscarCategoriaPorid(id); //retorna respuesta al cliente
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> postCrearCategoria(@RequestBody EntityCategoria request){ //localhost:8080/api/v1/libros
        return categoriaService.guardarCategoria(request); //retorna respuesta al cliente
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> postCrearCategoria(@RequestBody EntityCategoria request,@PathVariable  Long id){ //localhost:8080/api/v1/libros
        return categoriaService.actualizarCategoria (request,id); //retorna respuesta al cliente
    }
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> postCrearCategoria(@PathVariable  Long id){ //localhost:8080/api/v1/libros
        return categoriaService.eliminarCategoria (id); //retorna respuesta al cliente
    }
}
