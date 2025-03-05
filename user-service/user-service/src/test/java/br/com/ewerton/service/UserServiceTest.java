package br.com.ewerton.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.ewerton.model.User;
import br.com.ewerton.repositories.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks antes de cada teste
        MockitoAnnotations.openMocks(this);

        // Cria um usuário para os testes
        user = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com");
    }

    @Test
    void testAllUser() {
        // Mocka o comportamento do método findAll
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        // Chama o método que está sendo testado
        List<User> users = userService.allUser();

        // Verifica se a lista retornada é a mesma que foi mockada
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user.getName(), users.get(0).getName());
        assertEquals(user.getEmail(), users.get(0).getEmail());

        // Verifica se o método do repositório foi chamado corretamente
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        // Mocka o comportamento do método save
        when(userRepository.save(user)).thenReturn(user);

        // Chama o método que está sendo testado
        User createdUser = userService.createUser(user);

        // Verifica se o usuário retornado é o mesmo que foi mockado
        assertNotNull(createdUser);
        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getEmail(), createdUser.getEmail());

        // Verifica se o método do repositório foi chamado corretamente
        verify(userRepository, times(1)).save(user);
    }
}
