package br.com.compassuol.pb.challenge.products.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.compassuol.pb.challenge.products.dtos.UserDTO;
import br.com.compassuol.pb.challenge.products.entities.User;
import br.com.compassuol.pb.challenge.products.services.UserService;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO("Tamires", "Xavier", "tamires.xavier@gmail.com", "tamiresx7*");

        User user = new User("Tamires", "Xavier", "tamires.xavier@gmail.com", "tamiresx7*");
        when(userService.createUser(userDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserById() {
        Long id = 1L;

        User user = new User("Tamires", "Xavier", "tamires.xavier@gmail.com", "tamiresx7*");
        when(userService.getUserById(id)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;
        UserDTO userDTO = new UserDTO("Tamires", "Xavier", "tamires.xavier@gmail.com", "tamiresx7*");

        User user = new User("Tamires", "Xavier", "tamires.xavier@gmail.com", "tamiresx7*");
        when(userService.updateUser(id, userDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(id, userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

}