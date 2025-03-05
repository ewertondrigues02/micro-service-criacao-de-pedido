package br.com.ewerton.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que representa um usuário no sistema.
 * A classe é mapeada para a tabela "tb_user" no banco de dados.
 *
 * @author Ewerton Rodrigues
 * @version 1.0
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do usuário.
     * Utiliza UUID gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Nome do usuário.
     */
    private String name;

    /**
     * Endereço de e-mail do usuário.
     */
    private String email;

    /**
     * Construtor padrão.
     * Inicializa um objeto User sem definir os atributos.
     */
    public User() {
    }

    /**
     * Construtor com parâmetros para criar um usuário com id, nome e e-mail.
     *
     * @param id    Identificador único do usuário
     * @param name  Nome do usuário
     * @param email E-mail do usuário
     */
    public User(UUID id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Obtém o identificador único do usuário.
     *
     * @return O identificador único do usuário.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Define o identificador único do usuário.
     *
     * @param id O identificador único a ser atribuído.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Obtém o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do usuário.
     *
     * @param name O nome a ser atribuído ao usuário.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o e-mail do usuário.
     *
     * @return O e-mail do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     *
     * @param email O e-mail a ser atribuído ao usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna uma representação em String do objeto User.
     *
     * @return String contendo os detalhes do usuário.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }

    /**
     * Calcula o código hash do objeto User.
     * Utiliza os campos id, name e email para o cálculo.
     *
     * @return O código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, id, name);
    }

    /**
     * Compara se o objeto fornecido é igual ao objeto User.
     * Dois objetos User são considerados iguais se os campos id, name e email forem iguais.
     *
     * @param obj O objeto a ser comparado com o usuário atual.
     * @return True se os objetos forem iguais, caso contrário, false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }
}
