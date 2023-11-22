package br.com.fatecdiadema.projetotoyoyamongodb.web.controller;

import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class UsuarioController {

    /* usuarioRepository é a instanciação da classe UsuarioRepository */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllPostos() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.size() > 0) {
            return new ResponseEntity <List<UsuarioModel>>(usuarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioModel usuario) {
        try {
            usuario.setDataCriacao(new Date(System.currentTimeMillis()));
            usuarioRepository.save(usuario);
            return new ResponseEntity<UsuarioModel>(usuario, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
