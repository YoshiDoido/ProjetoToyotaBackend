package br.com.fatecdiadema.projetotoyoyamongodb.model;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Nome não pode ser nulo")
    @Field(name = "nome")
    private String nome;

    @NotNull(message = "Endereço não pode ser nulo")
    @Field(name = "endereco")
    private String endereco;

    @NotNull(message = "Telefone não pode ser nulo")
    @Field(name = "telefone")
    private String telefone;

    @NotNull(message = "Email não pode ser nulo")
    @Field(name = "email")
    private String email;

    @NotNull(message = "Senha não pode ser nulo")
    @Field(name = "senha")
    private String senha;

    @NotNull(message = "CNPJ não pode ser nulo")
    @CNPJ
    @Field(name = "cnpj")
    private String cnpj;

    @NotNull(message = "Horário de funcionamento não pode ser nulo")
    @Field(name = "horaFuncionamento")
    private String horarioFuncionamento;

    @Field(name = "dataCriacao")
    private Date dataCriacao;

    @Field(name = "dataModificacao")
    private Date dataModificacao;

}