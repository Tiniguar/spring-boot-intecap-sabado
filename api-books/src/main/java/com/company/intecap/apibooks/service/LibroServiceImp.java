package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.dao.ILibroDao;
import com.company.intecap.apibooks.model.EntityLibro;
import com.company.intecap.apibooks.response.LibroReponse;
import com.company.intecap.apibooks.response.LibroResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class LibroServiceImp implements  ILibroService{
    private static  final Logger log = Logger.getLogger(LibroServiceImp.class.getName());

    @Autowired //injecta el respositorio de libros
    private ILibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        log.info("Inicia metodo buscarLibros");
        LibroResponseRest libroReponse =  new LibroResponseRest();

        try{
            List<EntityLibro> libroList = (List<EntityLibro>) libroDao.findAll();
            libroReponse.getLibroReponse().setLibros(libroList);
            libroReponse.setMetadata("Respuesta exitosa","200","Lista de libros");
        }
        catch (Exception e){
            log.severe("Error en el metodo BuscarLibros"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            libroReponse.setMetadata("Error al consultar libro","500",e.getMessage());
            return  new ResponseEntity<LibroResponseRest>(libroReponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<LibroResponseRest>(libroReponse, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibroPorid(Long id) {
        log.info("Iniciar el metodo buscar libro por id");
        LibroResponseRest libroResponseRest = new LibroResponseRest();
        List<EntityLibro> libroList = new ArrayList<>();
        try {
            Optional<EntityLibro> libro = libroDao.findById(id);
            if(libro.isPresent()){
                libroList.add(libro.get());
                libroResponseRest.getLibroReponse().setLibros(libroList);
                libroResponseRest.setMetadata("Consulta exitosa","200","Se encontro el libro");
            }
            else{
                log.severe("No se encontro el libro por el id " + id);
                libroResponseRest.setMetadata("No existe el libro","404","No existe el id"+id);
                return  new ResponseEntity<LibroResponseRest>(libroResponseRest,HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            log.severe("Error en el metodo BuscarLibro por id"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            libroResponseRest.setMetadata("Error al consultar libro","500",e.getMessage());
            return  new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return   new ResponseEntity<LibroResponseRest>(libroResponseRest,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> guardarLibro(EntityLibro libro) {

        log.info("Iniciar el metodo guardar libro ");
        LibroResponseRest libroResponseRest = new LibroResponseRest();
        List<EntityLibro> libroList = new ArrayList<>();
        try {
            EntityLibro libroGuardado = libroDao.save(libro);


            if(libroGuardado != null ){
                libroList.add(libroGuardado);
                libroResponseRest.getLibroReponse().setLibros(libroList);
                libroResponseRest.setMetadata("guardado exitosa","200","Se registro el libro");
            }
            else{
                log.severe("No se guardo el libro por el id " + libro.getNombre());
                libroResponseRest.setMetadata("No se guardo el libro","400","No se puedo guardar "+libro.getNombre());
                return  new ResponseEntity<LibroResponseRest>(libroResponseRest,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        catch (Exception e){
            log.severe("Error en el metodo guardarLibro"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            libroResponseRest.setMetadata("Error al guardar libro","500",e.getMessage());
            return  new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return   new ResponseEntity<LibroResponseRest>(libroResponseRest,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> actualizarLibro(EntityLibro libro, Long id) {
        log.info("Iniciar el metodo actualizar  libro ");
        LibroResponseRest libroResponseRest = new LibroResponseRest();
        List<EntityLibro> libroList = new ArrayList<>();
        try {

            Optional<EntityLibro> libroBuscado = libroDao.findById(id);

            if(libroBuscado.isPresent()){
                libroBuscado.get().setNombre(libro.getNombre());
                libroBuscado.get().setAutor(libro.getAutor());
                libroBuscado.get().setDescripcion(libro.getDescripcion());

                EntityLibro libroActualizado = libroDao.save(libroBuscado.get());

                if(libroActualizado != null ){
                    libroList.add(libroActualizado);
                    libroResponseRest.getLibroReponse().setLibros(libroList);
                    libroResponseRest.setMetadata("actualizacion exitosa","200","Se actualizo el libro");
                }
                else{
                    log.severe("No se actualizar el libro por el id " + libro.getNombre());
                    libroResponseRest.setMetadata("No se guardo el libro","400","No se puedo guardar "+libro.getNombre());
                    return  new ResponseEntity<LibroResponseRest>(libroResponseRest,HttpStatus.INTERNAL_SERVER_ERROR);
                }

            }
            else {
                log.severe("No se encontro el libro con id " + id);
                libroResponseRest.setMetadata("No encontro el libro", "400", "No se encontro el id " + id);
                return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            log.severe("Error en el metodo actualizar libro"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            libroResponseRest.setMetadata("Error al guardar libro","500",e.getMessage());
            return  new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return   new ResponseEntity<LibroResponseRest>(libroResponseRest,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> eliminarLibro(Long id) {
        log.info("Iniciar el metodo eliminar  libro ");
        LibroResponseRest libroResponseRest = new LibroResponseRest();
        List<EntityLibro> libroList = new ArrayList<>();
        try {

            Optional<EntityLibro> libroBuscado = libroDao.findById(id);

            if (libroBuscado.isPresent()) {
                libroDao.delete(libroBuscado.get());
                libroResponseRest.setMetadata("Se elimino el libro", "200", "se elimino el libro con el id " + id);

            } else {
                log.severe("No se encontro el libro con id " + id);
                libroResponseRest.setMetadata("No encontro el libro", "404", "No se encontro el id " + id);
                return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            log.severe("Error en el metodo eliminar libro"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            libroResponseRest.setMetadata("Error al eliminar libro","500",e.getMessage());
            return  new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return   new ResponseEntity<LibroResponseRest>(libroResponseRest,HttpStatus.OK);
    }
}
