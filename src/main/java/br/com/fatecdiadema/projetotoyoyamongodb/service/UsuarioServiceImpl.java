package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.UsuarioCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.UsuarioRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioModel> getAllUsuarios()
    {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        if (usuarios.size() > 0)
        {
            return usuarios;
        } else
        {
            return new ArrayList<UsuarioModel>();
        }
    }

    @Override
    public UsuarioModel getSingleUsuario(String id) throws UsuarioCollectionException {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        if (!usuarioOptional.isPresent())
        {
            throw new UsuarioCollectionException(UsuarioCollectionException.NotFoundException(id));
        }
        else
        {
            return usuarioOptional.get();
        }
    }

    @Override
    public void createUsuario(UsuarioModel usuario) throws UsuarioCollectionException {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioOptional.isPresent())
        {
            throw new UsuarioCollectionException(UsuarioCollectionException.EmailAlreadyExists());
        }
        else
        {
            usuario.setDataCriacao(new Date(System.currentTimeMillis()));
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void updateUsuario(String id, UsuarioModel usuario) throws UsuarioCollectionException {
        Optional<UsuarioModel> usuarioWithId = usuarioRepository.findById(id);
        Optional<UsuarioModel> usuarioWithSameEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioWithId.isPresent()) {
            if (usuarioWithSameEmail.isPresent() && !usuarioWithSameEmail.get().getId().equals(id)) {
                throw new UsuarioCollectionException(UsuarioCollectionException.EmailAlreadyExists());
            }
            UsuarioModel usuarioToUpdate = usuarioWithId.get();

            usuarioToUpdate.setNome(usuario.getNome());
            usuarioToUpdate.setEmail(usuario.getEmail());
            usuarioToUpdate.setSenha(usuario.getSenha());
            usuarioToUpdate.setRole(usuario.getRole());
            usuarioToUpdate.setDataModificacao(new Date(System.currentTimeMillis()));
            usuarioRepository.save(usuarioToUpdate);
        }
        else
        {
            throw new UsuarioCollectionException(UsuarioCollectionException.NotFoundException(id));
        }
    }

        public void deleteUsuarioById(String id) throws UsuarioCollectionException {
            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
            if (!usuarioOptional.isPresent())
            {
                throw new UsuarioCollectionException(UsuarioCollectionException.NotFoundException(id));
            }
            else
            {
                usuarioRepository.deleteById(id);
            }
        }
    }
