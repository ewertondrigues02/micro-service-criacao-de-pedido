package br.com.ewerton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ewerton.model.User;
import br.com.ewerton.repositories.UserRepository;

/**
 * Classe de serviço responsável pela lógica de negócios relacionada aos usuários.
 * A classe utiliza o repositório UserRepository para realizar operações de CRUD no banco de dados.
 *
 * @author Ewerton Rodrigues
 * @version 1.0
 */
@Service
public class UserService {

    /**
     * Repositório de usuários, usado para acessar e manipular dados de usuários no banco de dados.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Retorna a lista de todos os usuários cadastrados no sistema.
     *
     * @return Uma lista de objetos {@link User} contendo todos os usuários no banco de dados.
     */
    public List<User> allUser() {
        return userRepository.findAll();
    }

    /**
     * Cria um novo usuário no sistema ou atualiza um usuário existente.
     *
     * @param obj O objeto {@link User} contendo os dados do usuário a ser criado ou atualizado.
     * @return O objeto {@link User} persistido no banco de dados após a operação.
     */
    public User createUser(User obj) {
        return userRepository.save(obj);
    }
}
