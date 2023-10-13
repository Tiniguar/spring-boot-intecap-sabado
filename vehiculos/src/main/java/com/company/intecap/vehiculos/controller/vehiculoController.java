package com.company.intecap.vehiculos.controller;

import com.company.intecap.vehiculos.model.Vehiculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// se una RestaController para indicar que la respuesta sera en formato json
//el endpoint esta disponible en localhost:8080/api/v2
@RestController
@RequestMapping("/api/v1")
public class vehiculoController {

    // para obtener el listado de vehiculos
    //localhost:8080/api/v2/vehiculos
    @GetMapping("/vehiculos")
    public ResponseEntity< List<Vehiculo>> getVehiculos(){
        // Se usa la entidad Vehiculo para la creacion del listado
        Vehiculo vehiculo1 =  new Vehiculo(1,"Mazda","CX-30.",2022,"Negro","P-345MBG",262900.00);
        Vehiculo vehiculo2 =  new Vehiculo(2,"Toyota","Yaris",2022,"Gris","P-345MBG",176090.00);
        Vehiculo vehiculo3 =  new Vehiculo(3,"Honda","CIVIC 5DR",2022,"Rojo","P-345MBG",310000.00);
        Vehiculo vehiculo4 =  new Vehiculo(4,"VW","Amarok V6",2022,"Azul","P-345MBG",424900.00);
        Vehiculo vehiculo5 =  new Vehiculo(5,"Hyundai","TUCSON GLS",2022,"Gris","P-345MBG",279990.00);
        // Se agrega cada vehiculo al listado
        List<Vehiculo> lista = List.of(vehiculo1,vehiculo2,vehiculo3,vehiculo4,vehiculo5);
        // se una responseEntity para enviar el 200 a cada peticion a esta url
        return new ResponseEntity<>(lista, HttpStatus.OK) ;


    }
}
