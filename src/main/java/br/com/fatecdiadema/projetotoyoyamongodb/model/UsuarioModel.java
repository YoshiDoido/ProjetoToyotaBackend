package br.com.fatecdiadema.projetotoyoyamongodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="usuarios")
/*depois mudar o nome teste para o nome da coleção que pretende usar */
public class UsuarioModel {

    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private Role role;

    private Date dataCriacao;

    private Date dataModificacao;

    /* Insira aqui os cargos de usuários que o sistema terá*/
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
