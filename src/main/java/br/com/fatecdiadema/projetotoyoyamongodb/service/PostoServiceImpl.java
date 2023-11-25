package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.PostoModel;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.PostoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostoServiceImpl implements PostoService {

    @Autowired
    private PostoRepository postoRepository;

    @Override
    public List<PostoModel> getAllPostos() {
        List<PostoModel> postos = postoRepository.findAll();
        if (postos.size() > 0) {
            return postos;
        } else {
            return new ArrayList<PostoModel>();
        }
    }

    @Override
    public PostoModel getSinglePosto(String id) throws PostoCollectionException {
        Optional<PostoModel> postoOptional = postoRepository.findById(id);
        if (!postoOptional.isPresent()) {
            throw new PostoCollectionException(PostoCollectionException.NotFoundException(id));
        } else {
            return postoOptional.get();
        }
    }

    @Override
    public void createPosto(PostoModel posto) throws ConstraintViolationException, PostoCollectionException {
        Optional<PostoModel> postoOptional = postoRepository.findByEmail(posto.getEmail());
        if (postoOptional.isPresent()) {
            throw new PostoCollectionException(PostoCollectionException.EmailAlreadyExists());
        } else {
            posto.setDataCriacao(new Date(System.currentTimeMillis()));
            postoRepository.save(posto);
        }
    }

    @Override
    public void updatePosto(String id, PostoModel posto) throws PostoCollectionException {
        Optional<PostoModel> postoWithId = postoRepository.findById(id);
        Optional<PostoModel> postoWithSameEmail = postoRepository.findByEmail(posto.getEmail());
        if (postoWithId.isPresent())
        {
            if (postoWithSameEmail.isPresent() && !postoWithSameEmail.get().getId().equals(id))
            {
                throw new PostoCollectionException(PostoCollectionException.EmailAlreadyExists());
            }
            PostoModel postoToUpdate = postoWithId.get();

            postoToUpdate.setNome(posto.getNome());
            postoToUpdate.setEndereco(posto.getEndereco());
            postoToUpdate.setTelefone(posto.getTelefone());
            postoToUpdate.setEmail(posto.getEmail());
            //postoToUpdate.setSenha(posto.getSenha());
            //postoToUpdate.setCnpj(posto.getCnpj());
            postoToUpdate.setDataModificacao(new Date(System.currentTimeMillis()));
            postoRepository.save(postoToUpdate);
        }
        else
        {
            throw new PostoCollectionException(PostoCollectionException.NotFoundException(id));
        }
    }

    public void deletePostoById(String id) throws PostoCollectionException {
        Optional<PostoModel> postoOptional = postoRepository.findById(id);
        if (!postoOptional.isPresent())
        {
            throw new PostoCollectionException(PostoCollectionException.NotFoundException(id));
        }
        else
        {
            postoRepository.deleteById(id);
        }
    }
}