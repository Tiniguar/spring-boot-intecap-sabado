package com.intecap.ctiniguar.Articulos.services;

import com.intecap.ctiniguar.Articulos.models.Articulo;
import com.intecap.ctiniguar.Articulos.models.Fabricante;
import com.intecap.ctiniguar.Articulos.models.dao.IFabricanteDao;
import com.intecap.ctiniguar.Articulos.response.ArticuloResponseRest;
import com.intecap.ctiniguar.Articulos.response.FabricanteResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class FabricanteService implements IFabricanteService {
    //necesitamos un instancia para imprimir en el log de server;
    private static  final Logger log = Logger.getLogger(FabricanteService.class.getName());

    //inyeccion de despencias es  este caso necesitamos  repository Fabricante
    @Autowired

    private IFabricanteDao fabricanteDao;
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<FabricanteResponseRest> buscarFabricantes() {

        // imprime en el log la llamada del metodo buscar fabricantes
        log.info("Inicia metodo buscarFabricantes");
        // se instancia la repuesta
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();

        try{
            // se hace la consulta a la base de datos y encuentra todos los fabricantes
            List<Fabricante> fabricanteList = (List<Fabricante>) fabricanteDao.findAll();
            // el resultado se asignar a los fabricantes de la respuesta.
            fabricanteResponseRest.getFabricanteResponse().setFabricantes(fabricanteList);
            // se ingresar la metadato de informacion de la cabecera.
            fabricanteResponseRest.setMetadata("Respuesta existosa","200","Lista de fabricantes");
        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al buscar fabricantes: " +e.getMessage());
            log.severe(" Error al buscar fabricantes: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            fabricanteResponseRest.setMetadata("Error al consultar fabricantes","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorid(Long id) {

        // imprime en el log la llamada del metodo buscar fabricantes
        log.info("Inicia metodo buscarFabricantes");
        // se instancia la repuesta
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();

        try{

            List<Fabricante> fabricanteList = new ArrayList<>();
            // se hace la consulta a la base de datos y encuentra el fabricante
            Optional<Fabricante> fabricanteBuscar =  fabricanteDao.findById(id);

            if(fabricanteBuscar.isPresent())
            {
                //se asigna a la lista
                fabricanteList.add(fabricanteBuscar.get());
                // el resultado se asignar a los fabricantes de la respuesta.
                fabricanteResponseRest.getFabricanteResponse().setFabricantes(fabricanteList);
                // se ingresar la metadato de informacion de la cabecera.
                fabricanteResponseRest.setMetadata("Respuesta existosa","200","Se encontro el fabricante");
            }
            else{
                log.severe("No se encontro el fabricante con el di: " +id);
                // se ingresar la metadato de informacion de la cabecera.
                fabricanteResponseRest.setMetadata("No se encontro","200","No se encontro el fabricante con el di: " +id);
                // se retorna el error al cliente
                return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al buscar fabricante: " +e.getMessage());
            log.severe(" Error al buscar fabricante: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            fabricanteResponseRest.setMetadata("Error al consultar fabricantes","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<FabricanteResponseRest> guardarFabricante(Fabricante fabricante) {
        // imprime en el log la llamada del metodo buscar fabricantes
        log.info("Inicia metodo buscarFabricantes");
        // se instancia la repuesta
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();

        try{

            List<Fabricante> fabricanteList = new ArrayList<>();
            // se hace el guardado a la base de datos
            Fabricante fabricanteCrear =  fabricanteDao.save(fabricante);

            if(fabricanteCrear != null)
            {
                //se asigna a la lista
                fabricanteList.add(fabricanteCrear);
                // el resultado se asignar a los fabricantes de la respuesta.
                fabricanteResponseRest.getFabricanteResponse().setFabricantes(fabricanteList);
                // se ingresar la metadato de informacion de la cabecera.
                fabricanteResponseRest.setMetadata("Respuesta existosa","200","Se creo el fabricante");
            }
            else{
                log.severe("No se pudo crrear el fabricante ");
                // se ingresar la metadato de informacion de la cabecera.
                fabricanteResponseRest.setMetadata("No se pudo crrear el fabricante","200","No se pudo crear  el fabricante con los datos enviado ");
                // se retorna el error al cliente
                return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            //cunado se occure un se imprime el log
            log.severe(" Error al crear fabricante: " + e.getMessage());
            log.severe(" Error al guardar fabricante: " + e.getStackTrace());
            // se ingresa los metadaos con la informacion
            fabricanteResponseRest.setMetadata("Error al consultar fabricantes", "500", e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // se retona la respuesta al cliente
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long id) {
        // imprime en el log la llamada del metodo buscar fabricantes
        log.info("Inicia metodo buscarFabricantes");
        // se instancia la repuesta
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();

        try{

            List<Fabricante> fabricanteList = new ArrayList<>();
            // se hace la consulta a la base de datos
            Optional<Fabricante> fabricanteBuscar =  fabricanteDao.findById(id);

            if(fabricanteBuscar.isPresent())
            {
                //cambios
                fabricanteBuscar.get().setNombre(fabricante.getNombre());
                // se guardar nuevamente
                Fabricante fabricanteCrear =  fabricanteDao.save(fabricanteBuscar.get());

                if(fabricanteCrear != null)
                {
                    //se asigna a la lista
                    fabricanteList.add(fabricanteCrear);
                    // el resultado se asignar a los fabricantes de la respuesta.
                    fabricanteResponseRest.getFabricanteResponse().setFabricantes(fabricanteList);
                    // se ingresar la metadato de informacion de la cabecera.
                    fabricanteResponseRest.setMetadata("Respuesta existosa","200","Se actualizo  el fabricante");
                }
                else{
                    log.severe("No se pudo guardar los cambios  al fabricante ");
                    // se ingresar la metadato de informacion de la cabecera.
                    fabricanteResponseRest.setMetadata("No se pudo crrear el fabricante","200","No se pudo crear  el fabricante con los datos enviado" +fabricante.getNombre() );
                    // se retorna el error al cliente
                    return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
                }
            }
            else{
                log.severe("No se encontro el fabricante con el di: " +id);
                // se ingresar la metadato de informacion de la cabecera.
                fabricanteResponseRest.setMetadata("No se encontro","200","No se encontro el fabricante con el di: " +id);
                // se retorna el error al cliente
                return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al buscar fabricante: " +e.getMessage());
            log.severe(" Error al buscar fabricante: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            fabricanteResponseRest.setMetadata("Error al consultar fabricantes","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id) {
        // imprime en el log la llamada del metodo buscar fabricantes
        log.info("Inicia metodo eliminarFabricante");
        // se instancia la repuesta
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();

        try{
            // se hace la consulta a la base de datos y encuentra el fabricante


            Optional<Fabricante> fabricanteBuscar =  fabricanteDao.findById(id);

            if(fabricanteBuscar.isPresent())
            {
                //se elimina
                fabricanteDao.delete(fabricanteBuscar.get());

                // se ingresar la metadato de informacion de la cabecera.
                fabricanteResponseRest.setMetadata("Eliminacion existosa","200","Se elimino el fabricante");
            }
            else{
                log.severe("No se encontro el fabricante con el di: " +id);
                // se ingresar la metadato de informacion de la cabecera.
                fabricanteResponseRest.setMetadata("No se encontro","200","No se encontro el fabricante con el di: " +id);
                // se retorna el error al cliente
                return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al eliminar fabricante: " +e.getMessage());
            log.severe(" Error al eliminar fabricante: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            fabricanteResponseRest.setMetadata("Error al eliminar fabricante","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existeNombreFabricante(String nombre) {
        Optional<Fabricante> fabricante = fabricanteDao.findByNombre(nombre);
        return fabricante.isPresent();
    }

}
