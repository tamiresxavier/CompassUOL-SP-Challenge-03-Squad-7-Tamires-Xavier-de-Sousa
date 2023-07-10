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

import br.com.compassuol.pb.challenge.products.dtos.RoleDTO;
import br.com.compassuol.pb.challenge.products.entities.Role;
import br.com.compassuol.pb.challenge.products.services.RoleService;


@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO roleDTO) {
    	Role role = roleService.createRole(roleDTO);
        return ResponseEntity.ok().body(role);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
    	Role role = roleService.getRoleById(id);
        return ResponseEntity.ok().body(role);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
    	Role role = roleService.updateRole(id, roleDTO);
        return ResponseEntity.ok().body(role);
    }
    
}