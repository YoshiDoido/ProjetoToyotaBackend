package br.com.fatecdiadema.projetotoyoyamongodb.web.controller;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.PostoModel;
import br.com.fatecdiadema.projetotoyoyamongodb.service.PostoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/postos") /* Originalmente @RequestMapping("/") */
public class PostoController {

    @Autowired
    private PostoService postoService;

    /* Armazena postos inseridos pelo usuário para dentro do MongoDB */
    @PostMapping("/postos") /* Originalmente @PostMapping("/postos") */
    public ResponseEntity<?> createPosto(@RequestBody PostoModel posto) {
        try {
            postoService.createPosto(posto);
            return new ResponseEntity<PostoModel>(posto, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (PostoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /* Busca e retorna postos armazenados no MongoDB para o usuário- Requisição GET */
    @GetMapping("/postos")
    public ResponseEntity<?> getAllPostos() {
        List<PostoModel> postos = postoService.getAllPostos();
        return new ResponseEntity<>(postos, postos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/postos/{id}") /* Originalmente @GetMapping("/postos/{id}") */
    public ResponseEntity<?> getSinglePosto(@PathVariable("id") String id) {
        try
        {
            return new ResponseEntity<>(postoService.getSinglePosto(id), HttpStatus.OK);
        }
        catch (PostoCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/postos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws PostoCollectionException {
        try {
            postoService.deletePostoById(id);
            return new ResponseEntity<>("Posto deletado com id " + id, HttpStatus.OK);
        }
        catch (PostoCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/postos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody PostoModel posto) {
        try
        {
            postoService.updatePosto(id, posto);
            return new ResponseEntity<>("Posto com id" +id+ "atualizado com sucesso!", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (PostoCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
