package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.UsuarioCollectionException;
import jakarta.validation.ConstraintViolationException;

public interface UsuarioService {

    public void createUsuario(String nome, String email, String senha) throws ConstraintViolationException, UsuarioCollectionException;
}
