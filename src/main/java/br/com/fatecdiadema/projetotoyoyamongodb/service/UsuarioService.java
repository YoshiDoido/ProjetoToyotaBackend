package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.UsuarioCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface UsuarioService {

    public List<UsuarioModel> getAllUsuarios();

    public UsuarioModel getSingleUsuario(String id) throws UsuarioCollectionException;

    public void createUsuario(UsuarioModel usuario) throws ConstraintViolationException, UsuarioCollectionException;

    public void updateUsuario(String id, UsuarioModel usuario) throws UsuarioCollectionException;

    public void deleteUsuarioById(String id) throws UsuarioCollectionException;
}
