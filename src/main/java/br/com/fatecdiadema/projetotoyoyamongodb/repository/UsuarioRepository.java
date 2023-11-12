package br.com.fatecdiadema.projetotoyoyamongodb.repository;

import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel, String> {

}
