package br.com.fatecdiadema.projetotoyoyamongodb.repository;

import br.com.fatecdiadema.projetotoyoyamongodb.model.PostoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostoRepository extends MongoRepository<PostoModel, String> {

    @Query("{'nome': ?0}")
    Optional<PostoModel> findByNome(String nome);

    @Query("{'cnpj': ?0}")
    Optional<PostoModel> findByCnpj(String cnpj);

    @Query("{'email': ?0}")
    Optional<PostoModel> findByEmail(String email);

    @Query("{'_id': ?0}")
    Optional<PostoModel> findById(String s);

    @Query("{'nome': ?0, 'cnpj': ?1}")
    Optional<PostoModel> findByNomeAndCnpj(String nome, String cnpj);
}
