package br.com.ewerton.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ewerton.model.User;
import br.com.ewerton.service.UserService;

/**
 * Controlador para gerenciamento de usuários.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Obtém a lista de todos os usuários.
     *
     * @return Lista de usuários cadastrados.
     */
    @GetMapping
    public ResponseEntity<List<User>> userAll() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Cria um novo usuário.
     *
     * @param user Dados do usuário a ser criado.
     * @return Resposta com o URI do usuário criado.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
