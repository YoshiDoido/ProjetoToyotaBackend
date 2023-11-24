package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.PostoModel;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface PostoService {

    public List<PostoModel> getAllPostos();

    public PostoModel getSinglePosto(String id) throws PostoCollectionException;

    public void createPosto(String nome, String endereco, String cnpj, String horarioFuncionamento, String telefone, String email, String senha) throws ConstraintViolationException, PostoCollectionException;

    public void updatePosto(String id, PostoModel posto) throws PostoCollectionException;

    public void deletePostoById(String id) throws PostoCollectionException;
}
