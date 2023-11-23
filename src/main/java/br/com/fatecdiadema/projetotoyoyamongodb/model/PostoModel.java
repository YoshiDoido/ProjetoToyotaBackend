package br.com.fatecdiadema.projetotoyoyamongodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "postos")
public class PostoModel {

    @Id
    private String id;

    @Field(name = "nome")
    private String nome;

    @Field(name = "endereco")
    private String endereco;

    @Field(name = "telefone")
    private String telefone;

    @Field(name = "email")
    private String email;

    @Field(name = "senha")
    private String senha;

    @CNPJ
    @Field(name = "cnpj")
    private String cnpj;

    @Field(name = "horaFuncionamento")
    private String horaFuncionamento;

    @Field(name = "dataCriacao")
    private Date dataCriacao;

    @Field(name = "dataModificacao")
    private Date dataModificacao;

}
