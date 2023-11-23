package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.UsuarioCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.UsuarioRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void createUsuario(String username, String password, String email) throws ConstraintViolationException, UsuarioCollectionException {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByNome(username);
        if (usuarioOptional.isPresent()) {
            throw new UsuarioCollectionException("Usuario já existe");
        } else {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setEmail(email);
            usuario.setDataCriacao(new Date(System.currentTimeMillis()));
            usuarioRepository.save(usuario);
        }
    }
}