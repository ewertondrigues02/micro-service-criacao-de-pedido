package br.com.ewerton.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ewerton.model.User;

/**
 * Interface responsável pelo acesso aos dados dos usuários no banco de dados.
 * Extende JpaRepository para fornecer métodos padrão de persistência de dados
 * sem a necessidade de implementação.
 *
 * @author Ewerton Rodrigues
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // A interface herda os métodos padrão de persistência do JpaRepository, como:
    // - save(): para salvar um novo ou atualizar um usuário existente.
    // - findById(): para buscar um usuário pelo seu identificador.
    // - findAll(): para listar todos os usuários.
    // - deleteById(): para remover um usuário pelo seu identificador.
    // Não é necessário adicionar implementações, pois o Spring Data JPA cuida disso.
}
