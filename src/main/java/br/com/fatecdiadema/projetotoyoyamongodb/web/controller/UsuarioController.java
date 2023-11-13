package br.com.fatecdiadema.projetotoyoyamongodb.web.controller;

import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UsuarioController {

    /* usuarioRepository é a instanciação da classe UsuarioRepository */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /* Busca e retorna postos armazenados no MongoDB para o usuário- Requisição GET */
    @GetMapping("/postos")
    public ResponseEntity<?> getAllPostos() {
        List<UsuarioModel> postos = usuarioRepository.findAll();
        if (postos.size() > 0) {
            return new ResponseEntity<List<UsuarioModel>>(postos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum posto encontrado", HttpStatus.NOT_FOUND);
        }
    }

    /* Armazena postos inseridos pelo usuário para dentro do MongoDB */
    @PostMapping("/postos")
    public ResponseEntity<?> createPosto(@RequestBody UsuarioModel posto) {
        try {
            posto.setDataCriacao(new Date(System.currentTimeMillis()));
            usuarioRepository.save(posto);
            return new ResponseEntity<UsuarioModel>(posto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("postos/{id}")
    public ResponseEntity<?> getSinglePosto(@PathVariable("id") String id) {
        Optional<UsuarioModel> postoOptional = usuarioRepository.findById(id);
        if(postoOptional.isPresent()) {
            return new ResponseEntity<>(postoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum posto encontrado com id" +id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("postos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody UsuarioModel posto) {
        Optional<UsuarioModel> postoOptional = usuarioRepository.findById(id);
        if(postoOptional.isPresent()) {
            UsuarioModel postoToSave = postoOptional.get();
            /* Código que não tenho muita certeza o que faz mas dá certo */
            /* postoToSave() é o método para salvar dados */
            postoToSave.setUsername(posto.getUsername() != null ? posto.getUsername() : postoToSave.getUsername());
            postoToSave.setPassword(posto.getPassword() != null ? posto.getPassword() : postoToSave.getPassword());
            postoToSave.setEmail(posto.getEmail() != null ? posto.getEmail() : postoToSave.getEmail());
            postoToSave.setRole(posto.getRole() != null ? posto.getRole() : postoToSave.getRole());
            postoToSave.setDataModificacao(new Date(System.currentTimeMillis()));
            usuarioRepository.save(postoToSave);
            return new ResponseEntity<>(postoToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum posto encontrado com id" +id, HttpStatus.NOT_FOUND);
        }
    }
}
