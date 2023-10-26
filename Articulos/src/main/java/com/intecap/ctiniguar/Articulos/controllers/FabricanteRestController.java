package com.intecap.ctiniguar.Articulos.controllers;

import com.intecap.ctiniguar.Articulos.models.Fabricante;
import com.intecap.ctiniguar.Articulos.response.ArticuloResponseRest;
import com.intecap.ctiniguar.Articulos.response.FabricanteResponseRest;
import com.intecap.ctiniguar.Articulos.services.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FabricanteRestController {

    @Autowired
    private IFabricanteService fabricanteService;

    // reponse por la url localhost:8081/api/v1/fabricantes
    @GetMapping("/fabricantes")
    public ResponseEntity<FabricanteResponseRest> getFabricantes(){
        //retorna ls fabricantes encontrado.
        return  fabricanteService.buscarFabricantes();
    }

    // reponse por la url localhost:8081/api/v1/fabricantes/1
    @GetMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> getFabricantePorId(@PathVariable Long id){
        //retorna el fabricante encontrado.
        return  fabricanteService.buscarFabricantePorid(id);
    }

    // reponse por la url localhost:8081/api/v1/fabricantes
    @PostMapping("/fabricantes")
    public ResponseEntity<FabricanteResponseRest> putFabricantes(@RequestBody Fabricante request){
        //retorna repuesta de creacion
        return  fabricanteService.guardarFabricante(request);
    }

    // reponse por la url localhost:8081/api/v1/fabricantes/1
    @PutMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> putFabricantes(@RequestBody Fabricante request, @PathVariable Long id){
        //retorna respuesta de la actualizacion
        return  fabricanteService.actualizarFabricante(request,id);
    }

    // reponse por la url localhost:8081/api/v1/fabricantes/1
    @DeleteMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> deleteFabricantes( @PathVariable Long id){
        //retorna la respuesta de la elimiancion de fabricante.
        return  fabricanteService.eliminarFabricante(id);
    }



}

