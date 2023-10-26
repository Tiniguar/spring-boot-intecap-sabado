package com.intecap.ctiniguar.Articulos.services;

import com.intecap.ctiniguar.Articulos.models.Articulo;
import com.intecap.ctiniguar.Articulos.models.dao.IArticuloDao;
import com.intecap.ctiniguar.Articulos.models.dao.IFabricanteDao;
import com.intecap.ctiniguar.Articulos.response.ArticuloResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class ArticuloService implements  IArticuloService {
    private static  final Logger log = Logger.getLogger(ArticuloService.class.getName());

    //inyeccion de despencias es  este caso necesitamos  repository Articulo
    @Autowired
    private IArticuloDao articuloDao;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ArticuloResponseRest> buscaArticulo() {
        // imprime en el log la llamada del metodo buscar articulos
        log.info("Inicia metodo buscaArticulo");
        // se instancia la repuesta
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();

        try{
            // se hace la consulta a la base de datos y encuentra todos los articulos
            List<Articulo> articuloList = (List<Articulo>) articuloDao.findAll();
            // el resultado se asignar a los articulos de la respuesta.
            articuloResponseRest.getArticuloResponse().setArticulos(articuloList);
            // se ingresar la metadato de informacion de la cabecera.
            articuloResponseRest.setMetadata("Respuesta existosa","200","Lista de articulos");
        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al buscar articulos: " +e.getMessage());
            log.severe(" Error al buscar articulos: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            articuloResponseRest.setMetadata("Error al consultar articulos","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorid(Long id) {
        // imprime en el log la llamada del metodo buscar articulos por id
        log.info("Inicia metodo buscarArticuloPorid");
        // se instancia la repuesta
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();

        try{
            List<Articulo> articuloList = new ArrayList<>();
            //buscar el articulos por el id enviado
            Optional<Articulo> articulo = articuloDao.findById(id);
            // verificar si la consulta encuentra el articulo
            if(articulo.isPresent()){
                articuloList.add(articulo.get()); // el resultado se asignar a los articulos de la respuesta.
                articuloResponseRest.getArticuloResponse().setArticulos(articuloList);
                // se ingresar la metadato de informacion de la cabecera.
                articuloResponseRest.setMetadata("Respuesta existosa","200","Lista de articulos");
            }
            else{
                log.severe("No se encontro el articulo por el id " + id);
                articuloResponseRest.setMetadata("No existe el articulo","404","No existe el id"+id);
                return  new ResponseEntity<ArticuloResponseRest>(articuloResponseRest,HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al buscar articulo: " +e.getMessage());
            log.severe(" Error al buscar articulo: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            articuloResponseRest.setMetadata("Error al consultar articulo por id","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<ArticuloResponseRest> guardarArticulo(Articulo articulo) {
        // imprime en el log la llamada del metodo buscar articulos
        log.info("Inicia metodo buscaArticulo");
        // se instancia la repuesta
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();

        try{
            List<Articulo> articuloList = new ArrayList<>();

            // se hace la consulta a la base de datos y encuentra todos los articulos
            Articulo articuloGuardado =  articuloDao.save(articulo);
            if(articuloGuardado != null) {
                articuloList.add(articuloGuardado);
                // el resultado se asignar a los articulos de la respuesta.
                articuloResponseRest.getArticuloResponse().setArticulos(articuloList);
                // se ingresar la metadato de informacion de la cabecera.
                articuloResponseRest.setMetadata("Respuesta existosa","200","Articulo agregado");
            }
            else {

                // se ingresar la metadato de informacion de la cabecera.
                articuloResponseRest.setMetadata("No se guardo Articulo","400","No se pudo crear el articulo");
                // se retorna el error al cliente
                return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al crear articulo: " +e.getMessage());
            log.severe(" Error al agregar articulo: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            articuloResponseRest.setMetadata("Error al agregar articulo","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long id) {
        // imprime en el log la llamada del metodo eliminarArticulo
        log.info("Inicia metodo eliminarArticulo");
        // se instancia la repuesta
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();

        try{

            List<Articulo> articuloList = new ArrayList<>();

            //buscar el articulos por el id enviado
            Optional<Articulo> articuloencontrado = articuloDao.findById(id);
            // verificar si la consulta encuentra el articulo
            if(articuloencontrado.isPresent()){
                // se actualiza los cmapos
               articuloencontrado.get().setNombre(articulo.getNombre());
               articuloencontrado.get().setPrecio(articulo.getPrecio());
               articuloencontrado.get().setFabricante(articulo.getFabricante());
               // articuloencontrado.
                Articulo guardarArticulo = articuloDao.save(articuloencontrado.get());

                if(guardarArticulo != null){

                    articuloList.add(guardarArticulo);
                    articuloResponseRest.getArticuloResponse ().setArticulos(articuloList);
                    articuloResponseRest.setMetadata("actualizacion exitosa","200","Se actualizo el articulo");
                }
                // se responde que no se pudo actualizar
                else{
                    log.severe("No se actualiza el  por el id " + id);
                    articuloResponseRest.setMetadata("No se guardo el articulo","400","No se puedo guardar el id " + id);
                    return  new ResponseEntity<ArticuloResponseRest>(articuloResponseRest,HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            else{
                log.severe("No se encontro el articulo para eliminar " + id);
                articuloResponseRest.setMetadata("No existe el articulo para eliminar","404","No existe el id musica "+id);
                return  new ResponseEntity<ArticuloResponseRest>(articuloResponseRest,HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al eliminar articulo: " +e.getMessage());
            log.severe(" Error al eliminar articulo: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            articuloResponseRest.setMetadata("Error al eliminar el articulo por id","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);

    }
    @Transactional
    @Override
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id) {
        // imprime en el log la llamada del metodo eliminarArticulo
        log.info("Inicia metodo eliminarArticulo");
        // se instancia la repuesta
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();

        try{

            //buscar el articulos por el id enviado
            Optional<Articulo> articulo = articuloDao.findById(id);
            // verificar si la consulta encuentra el articulo
            if(articulo.isPresent()){
                // se elimina el registro
                articuloDao.delete(articulo.get());

                // se ingresar la metadato de informacion de la cabecera.
                articuloResponseRest.setMetadata("Eliminacion existosa","200","Se elimino de los articulos");
            }
            else{
                log.severe("No se encontro el articulo para eliminar " + id);
                articuloResponseRest.setMetadata("No existe el articulo para eliminar","404","No existe el id musica "+id);
                return  new ResponseEntity<ArticuloResponseRest>(articuloResponseRest,HttpStatus.NOT_FOUND);
            }


        }catch (Exception e){
            //cunado se occure un se imprime el log
            log.severe(" Error al eliminar articulo: " +e.getMessage());
            log.severe(" Error al eliminar articulo: " +e.getStackTrace());
            // se ingresa los metadaos con la informacion
            articuloResponseRest.setMetadata("Error al eliminar el articulo por id","500",e.getMessage());
            // se retorna el error al cliente
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // se retona la respuesta al cliente
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }
}
