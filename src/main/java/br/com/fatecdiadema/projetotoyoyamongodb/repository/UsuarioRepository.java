package br.com.fatecdiadema.projetotoyoyamongodb.repository;

import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel, String> {

    @Query("{'nome': ?0}")
    Optional<UsuarioModel> findByNome(String nome);

    @Query("{'email': ?0}")
    Optional<UsuarioModel> findByEmail(String email);

    @Query("{'nome': ?0, 'senha': ?1}")
    Optional<UsuarioModel> findByNomeAndSenha(String nome, String senha);
}
