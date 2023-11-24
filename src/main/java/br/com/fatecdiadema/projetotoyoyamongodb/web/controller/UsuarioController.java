package br.com.fatecdiadema.projetotoyoyamongodb.web.controller;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.exception.UsuarioCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.service.UsuarioService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioModel usuario) {
        try {
            usuarioService.createUsuario(usuario);
            return new ResponseEntity<UsuarioModel>(usuario, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (UsuarioCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getSingleUsuario(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(usuarioService.getSingleUsuario(id), HttpStatus.OK);
        } catch (UsuarioCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            usuarioService.deleteUsuarioById(id);
            return new ResponseEntity<>("Deletado usuário com id " + id, HttpStatus.OK);
        } catch (UsuarioCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateUsuarioById(@PathVariable("id") String id, @RequestBody UsuarioModel usuario) {
        try
        {
            usuarioService.updateUsuario(id, usuario);
            return new ResponseEntity<>("Atualizado usuário com id " + id, HttpStatus.OK);
        } catch (UsuarioCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
