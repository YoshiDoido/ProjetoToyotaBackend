package br.com.fatecdiadema.projetotoyoyamongodb.repository;

import br.com.fatecdiadema.projetotoyoyamongodb.model.PostoModel;
import br.com.fatecdiadema.projetotoyoyamongodb.model.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostoRepository extends MongoRepository<PostoModel, String> {

}
