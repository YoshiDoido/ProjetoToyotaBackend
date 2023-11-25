package br.com.fatecdiadema.projetotoyoyamongodb.model;

import jakarta.validation.constraints.Email;
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
    @Email(message = "Email inválido")
    private String email;

    /*
    // Não vai precisar de senha
    @NotNull(message = "Senha não pode ser nulo")
    @Field(name = "senha")
    private String senha;

     */

    /*
    // Também não vai precisar de CNPJ
    @NotNull(message = "CNPJ não pode ser nulo")
    @CNPJ
    @Field(name = "cnpj")
    private String cnpj;

     */
    @NotNull(message = "Horário de funcionamento não pode ser nulo")
    @Field(name = "horaFuncionamento")
    private String horarioFuncionamento;

    @NotNull(message = "Latitude não pode ser nulo")
    @Field(name = "latitude")
    private double latitude;

    @NotNull(message = "Longitude não pode ser nulo")
    @Field(name = "longitude")
    private double longitude;

    @Field(name = "dataCriacao")
    private Date dataCriacao;

    @Field(name = "dataModificacao")
    private Date dataModificacao;

}
