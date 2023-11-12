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
}
