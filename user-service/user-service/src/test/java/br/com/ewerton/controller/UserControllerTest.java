package br.com.ewerton.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.ewerton.model.User;
import br.com.ewerton.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Cria um usuário para os testes
        user = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com");
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Mocka o comportamento do método getAllUsers
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        // Faz uma requisição GET e verifica se a resposta está correta
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())  // Espera status 200 OK
                .andExpect(jsonPath("$[0].name").value(user.getName()))  // Verifica o nome
                .andExpect(jsonPath("$[0].email").value(user.getEmail()));  // Verifica o e-mail

        // Verifica se o método do serviço foi chamado
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testCreateUser() throws Exception {
        // Mocka o comportamento do método createUser
        when(userService.createUser(any(User.class))).thenReturn(user);

        // Cria o corpo da requisição com o objeto User
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        // Faz uma requisição POST e verifica se a resposta está correta
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())  // Espera status 201 Created
                .andExpect(header().string("Location", containsString("/users/")))  // Verifica o header Location
                .andExpect(jsonPath("$.name").value(user.getName()))  // Verifica o nome
                .andExpect(jsonPath("$.email").value(user.getEmail()));  // Verifica o e-mail

        // Verifica se o método do serviço foi chamado
        verify(userService, times(1)).createUser(any(User.class));
    }
}
