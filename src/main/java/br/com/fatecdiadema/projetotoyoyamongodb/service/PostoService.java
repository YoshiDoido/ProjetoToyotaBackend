package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import jakarta.validation.ConstraintViolationException;

public interface PostoService {

    public void createPosto(String nome, String endereco, String cnpj, String horarioFuncionamento, String telefone, String email, String senha) throws ConstraintViolationException, PostoCollectionException;
}
