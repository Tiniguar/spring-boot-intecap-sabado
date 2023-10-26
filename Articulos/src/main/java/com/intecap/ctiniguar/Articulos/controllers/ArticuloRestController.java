package com.intecap.ctiniguar.Articulos.controllers;

import com.intecap.ctiniguar.Articulos.models.Articulo;
import com.intecap.ctiniguar.Articulos.response.ArticuloResponseRest;
import com.intecap.ctiniguar.Articulos.services.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class ArticuloRestController {
    // por inyeccion de depencia se conectar con el servicio
    @Autowired
    private IArticuloService articuloService;

    // reponse por la url localhost:8081/api/v1/articulos
    @GetMapping("/articulos")
    public ResponseEntity<ArticuloResponseRest> getArticulos(){
        //retorna todos los articulos encontrados.
        return  articuloService.buscaArticulo();
    }

    // reponse por la url localhost:8081/api/v1/articulos/1
    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest> getArticulosPOrId(@PathVariable Long id){
        //retorna todos e articulo encontrado.
        return  articuloService.buscarArticuloPorid(id);
    }

    // reponse por la url localhost:8081/api/v1/articulos/
    @PostMapping("/articulos")
    public ResponseEntity<ArticuloResponseRest> postArticulos(@RequestBody Articulo request){
        //retorna repuesta de la creacion
        return  articuloService.guardarArticulo(request);
    }

    // reponse por la url localhost:8081/api/v1/articulos/1
    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(@RequestBody Articulo request, @PathVariable Long id){
        //retorna respuesta de la actualizacion
        return  articuloService.actualizarArticulo(request,id);
    }

    // reponse por la url localhost:8081/api/v1/articulos/1
    @DeleteMapping("/articulos/{id}")
    public ResponseEntity<ArticuloResponseRest> deleteArticulos(@PathVariable Long id){
        //retorna respuesta de la eliminacion.
        return  articuloService.eliminarArticulo(id);
    }
}
