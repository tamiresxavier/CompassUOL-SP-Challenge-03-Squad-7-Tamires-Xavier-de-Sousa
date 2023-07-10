package br.com.compassuol.pb.challenge.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.pb.challenge.products.dtos.UserDTO;
import br.com.compassuol.pb.challenge.products.entities.User;
import br.com.compassuol.pb.challenge.products.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
    	User user = userService.createUser(userDTO);
        return ResponseEntity.ok().body(user);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    	User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    	User user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok().body(user);
    }
    
}