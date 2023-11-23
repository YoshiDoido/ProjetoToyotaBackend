package br.com.fatecdiadema.projetotoyoyamongodb.service;

import br.com.fatecdiadema.projetotoyoyamongodb.exception.PostoCollectionException;
import br.com.fatecdiadema.projetotoyoyamongodb.model.PostoModel;
import br.com.fatecdiadema.projetotoyoyamongodb.repository.PostoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostoServiceImpl implements PostoService {

    @Autowired
    private PostoRepository postoRepository;

    @Override
    public void createPosto(String nome, String endereco, String cnpj, String horarioFuncionamento, String telefone, String email, String senha) throws ConstraintViolationException, PostoCollectionException {
        Optional<PostoModel> postoOptional = postoRepository.findByNome(nome);
        if (postoOptional.isPresent()) {
            throw new PostoCollectionException("Posto já existe");
        } else {
            PostoModel posto = new PostoModel();
            posto.setNome(nome);
            posto.setEndereco(endereco);
            posto.setCnpj(cnpj);
            posto.setHoraFuncionamento(horarioFuncionamento);
            posto.setTelefone(telefone);
            posto.setEmail(email);
            posto.setSenha(senha);
            postoRepository.save(posto);
        }
    }
}
