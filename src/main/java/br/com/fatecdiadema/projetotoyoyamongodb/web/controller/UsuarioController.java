package br.com.fatecdiadema.projetotoyoyamongodb.web.controller;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.UsuarioRepository;
import br.com.fatecdiadema.projetotoyoyamongodb.service.UsuarioService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
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
        } catch(ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch(PostoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllPostos() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.size() > 0) {
            return new ResponseEntity <List<UsuarioModel>>(usuarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getSingleUsuario(@PathVariable("id") String id) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum usuário encontrado com id" +id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody UsuarioModel usuario) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            UsuarioModel usuarioToSave = usuarioOptional.get();
            usuarioToSave.setUsername(usuario.getUsername());
            usuarioToSave.setPassword(usuario.getPassword());
            usuarioToSave.setEmail(usuario.getEmail());
            usuarioToSave.setRole(usuario.getRole());
            usuarioToSave.setDataModificacao(new Date(System.currentTimeMillis()));
            usuarioRepository.save(usuarioToSave);
            return new ResponseEntity<>(usuarioToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum usuário encontrado com id" +id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>("Usuário deletado com sucesso", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
