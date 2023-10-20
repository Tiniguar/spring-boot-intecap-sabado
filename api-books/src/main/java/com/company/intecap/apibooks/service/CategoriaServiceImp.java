package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.dao.ICategoriaDao;
import com.company.intecap.apibooks.dao.ILibroDao;
import com.company.intecap.apibooks.model.EntityCategoria;
import com.company.intecap.apibooks.model.EntityLibro;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
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
public class CategoriaServiceImp implements  ICategoriaService{
    private static  final Logger log = Logger.getLogger(LibroServiceImp.class.getName());

    @Autowired //injecta el respositorio de libros
    private ICategoriaDao categoriaDao;

    @Override
    //@Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {

        log.info("Inicia metodo buscarCategorias");
        CategoriaResponseRest categoriaResponseRest =  new CategoriaResponseRest();

        try{
            List<EntityCategoria> categoriaList = (List<EntityCategoria>) categoriaDao.findAll();
            categoriaResponseRest.getCategoriaResponse().setCategorias(categoriaList);
            categoriaResponseRest.setMetadata("Respuesta exitosa","200","Lista de libros");
        }
        catch (Exception e){
            log.severe("Error en el metodo BuscarLibros"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            categoriaResponseRest.setMetadata("Error al consultar libro","500",e.getMessage());
            return  new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CategoriaResponseRest> buscarCategoriaPorid(Long id) {
        log.info("Iniciar el metodo buscar categoria por id");
        CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
        List<EntityCategoria> categoriaList = new ArrayList<>();
        try {
            Optional<EntityCategoria> categoria = categoriaDao.findById(id);
            if(categoria.isPresent()){
                categoriaList.add(categoria.get());
                categoriaResponseRest.getCategoriaResponse().setCategorias(categoriaList);
                categoriaResponseRest.setMetadata("Consulta exitosa","200","Se encontro la categoria");
            }
            else{
                log.severe("No se encontro la categoria por el id " + id);
                categoriaResponseRest.setMetadata("No existe la categoria","404","No existe el id"+id);
                return  new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            log.severe("Error en el metodo BuscarCategoria por id"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            categoriaResponseRest.setMetadata("Error al consultar categoria","500",e.getMessage());
            return  new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return   new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaResponseRest> guardarCategoria(EntityCategoria categoria) {
       log.info("Iniciar el metodo guardar Categoria ");
        // instancias para la respuesta
       CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
       // lista que contiene los libro para la respuesta 
       List<EntityCategoria> categoriaList = new ArrayList<>();
        try {
            // se hace el almacenado del registo.
            EntityCategoria categoriaGuardadoa= categoriaDao.save(categoria);

            // se verificar si se almaceno
            if(categoriaGuardadoa != null ){
                // se agrega a la lista
                categoriaList.add(categoriaGuardadoa);
                // esta establece datos de para la respuesta  corrrecta
                categoriaResponseRest.getCategoriaResponse().setCategorias(categoriaList);
                categoriaResponseRest.setMetadata("guardado exitosa","200","Se registro el libro");
            }
            // es caso que ocurra un error al ingresar el registro
            else{
                log.severe("No se guardo el libro por el id " + categoria.getNombre());
                categoriaResponseRest.setMetadata("No se guardo el libro","400","No se puedo guardar "+categoria.getNombre());
                return  new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        // se captura el error
        catch (Exception e){
            log.severe("Error en el metodo guardarLibro"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            categoriaResponseRest.setMetadata("Error al guardar libro","500",e.getMessage());
            return  new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // retorna la respuesta correcta
        return   new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(EntityCategoria categoria, Long id) {
        log.info("Iniciar el metodo actualizar  categoria ");
        CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
        List<EntityCategoria> categoriaLista = new ArrayList<>();
        try {
            // se buscar la categoria
            Optional<EntityCategoria> categoriaBuscado = categoriaDao.findById(id);
            // si existe el id de la categoria
            if(categoriaBuscado.isPresent()){
                // se hace un set con los nuevos valores
                categoriaBuscado.get().setNombre(categoria.getNombre());
                categoriaBuscado.get().setDescripcion(categoria.getDescripcion());

                EntityCategoria categoriaActualizado = categoriaDao.save(categoriaBuscado.get());
                // se prepar la respuesta que fue actualizado con exito
                if(categoriaActualizado != null ){
                    categoriaLista.add(categoriaActualizado);
                    categoriaResponseRest.getCategoriaResponse ().setCategorias(categoriaLista);
                    categoriaResponseRest.setMetadata("actualizacion exitosa","200","Se actualizo la categoria");
                }
                // se responde que no se pudo actualizar
                else{
                    log.severe("No se actualiza la categoria por el id " + categoria.getNombre());
                    categoriaResponseRest.setMetadata("No se guardo la categoria","400","No se puedo guardar "+categoria.getNombre());
                    return  new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.INTERNAL_SERVER_ERROR);
                }

            }
            // si no existe el id enviado
            else {
                log.severe("No se encontro la categoriacon id " + id);
                categoriaResponseRest.setMetadata("No encontro la categoria", "400", "No se encontro el id " + id);
                return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.NOT_FOUND);
            }

        }
        // se captura la excepcion si hay un error
        catch (Exception e){
            log.severe("Error en el metodo actualizar categoria"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            categoriaResponseRest.setMetadata("Error al guardar categoria","500",e.getMessage());
            return  new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // retorna la respuesta con response 200
        return   new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id) {
        log.info("Se iniciar el metodo elimiar categoria");
        // se instancia una respuesta y lista de tipo categoria
        CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
        List<EntityCategoria> listCategoria = new ArrayList<>();

        try{
            // se buscar la categoria
            Optional<EntityCategoria> categoriaBuscado = categoriaDao.findById(id);
            // existe la categoria con el id
            if(categoriaBuscado.isPresent()){
                categoriaDao.delete(categoriaBuscado.get());
                categoriaResponseRest.setMetadata("Se elimina categoria","200","se elimino la categoria con el id "+ id);

            }
            //no existe el id
            else{
                log.severe("No se encontro la categoria con id"+ id);
                categoriaResponseRest.setMetadata("No se encontro la categoria","404","No se encontro el id "+ id);
                return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.NOT_FOUND);

            }

        }
        //se captura si ocurre un error
        catch (Exception e){
            log.severe("Error en el metodo eliminar categoiria"+e.getMessage());
            log.severe(Arrays.toString(e.getStackTrace()));
            categoriaResponseRest.setMetadata("Error al eliminar  categoria","500",e.getMessage());

        }

        return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest,HttpStatus.OK);
    }
}
