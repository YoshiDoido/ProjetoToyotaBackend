package br.com.fatecdiadema.projetotoyoyamongodb.web.controller;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.PostoModel;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.PostoRepository;
import br.com.fatecdiadema.projetotoyoyamongodb.service.PostoService;
import jakarta.validation.ConstraintViolationException;
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
//@RequestMapping("/postos") /* Originalmente @RequestMapping("/") */
public class PostoController {

    /* postoRepository é a instanciação da classe PostoRepository */
    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private PostoService postoService;

    /* Busca e retorna postos armazenados no MongoDB para o usuário- Requisição GET */
    @GetMapping("/postos") /* Originalmente @GetMapping("/postos") */
    public ResponseEntity<?> getAllPostos() {
        List<PostoModel> postos = postoRepository.findAll();
        if (postos.size() > 0) {
            return new ResponseEntity<List<PostoModel>>(postos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum posto encontrado", HttpStatus.NOT_FOUND);
        }
    }

    /* Armazena postos inseridos pelo usuário para dentro do MongoDB */
    @PostMapping("/postos") /* Originalmente @PostMapping("/postos") */
    public ResponseEntity<?> createPosto(@RequestBody PostoModel posto) {
        try {
            postoService.createPosto(posto);
            return new ResponseEntity<PostoModel>(posto, HttpStatus.OK);
        } catch(ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch(PostoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/postos/{id}") /* Originalmente @GetMapping("/postos/{id}") */
    public ResponseEntity<?> getSinglePosto(@PathVariable("id") String id) {
        Optional<PostoModel> postoOptional = postoRepository.findById(id);
        if(postoOptional.isPresent()) {
            return new ResponseEntity<>(postoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum posto encontrado com id" +id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/postos/{id}") /* Originalmente @PutMapping("/postos/{id}") */
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody PostoModel posto) {
        Optional<PostoModel> postoOptional = postoRepository.findById(id);
        if(postoOptional.isPresent()) {
            PostoModel postoToSave = postoOptional.get();
            /* Código que não tenho muita certeza o que faz mas dá certo */
            /* postoToSave() é o método para salvar dados */
            postoToSave.setNome(posto.getNome() != null ? posto.getNome() : postoToSave.getNome());
            postoToSave.setEndereco(posto.getEndereco() != null ? posto.getEndereco() : postoToSave.getEndereco());
            postoToSave.setTelefone(posto.getTelefone() != null ? posto.getTelefone() : postoToSave.getTelefone());
            postoToSave.setEmail(posto.getEmail() != null ? posto.getEmail() : postoToSave.getEmail());
            postoToSave.setCnpj(posto.getCnpj() != null ? posto.getCnpj() : postoToSave.getCnpj());
            postoToSave.setDataModificacao(new Date(System.currentTimeMillis()));
            postoRepository.save(postoToSave);
            return new ResponseEntity<>(postoToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nenhum posto encontrado com id" +id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/postos/{id}") /* Originalmente @DeleteMapping("/postos/{id}") */
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            postoRepository.deleteById(id);
            return new ResponseEntity<>("Posto deletado com o id "+id, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
