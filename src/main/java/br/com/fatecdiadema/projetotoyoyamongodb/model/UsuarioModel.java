package br.com.fatecdiadema.projetotoyoyamongodb.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="usuarios")
public class UsuarioModel {

    @Id
    private String id;

    @Field(name = "username")
    private String nome;

    @Field(name = "password")
    private String senha;

    @Field(name = "email")
    private String email;

    @Field(name = "role")
    private Role role;

    @Field(name = "dataCriacao")
    private Date dataCriacao;

    @Field(name = "dataModificacao")
    private Date dataModificacao;

    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        UsuarioModel usuario = (UsuarioModel) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}