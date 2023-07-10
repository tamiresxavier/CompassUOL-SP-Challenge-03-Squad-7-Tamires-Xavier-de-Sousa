package br.com.compassuol.pb.challenge.products.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.compassuol.pb.challenge.products.dtos.RoleDTO;
import br.com.compassuol.pb.challenge.products.entities.Role;
import br.com.compassuol.pb.challenge.products.services.RoleService;

@SpringBootTest
public class RoleControllerTest {

    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleService roleService;

    @Test
    public void testCreateRole() {
        RoleDTO roleDTO = new RoleDTO("Admin");
        Role role = new Role("Admin");
        Mockito.when(roleService.createRole(roleDTO)).thenReturn(role);

        ResponseEntity<Role> response = roleController.createRole(roleDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin", response.getBody().getName());
    }

    @Test
    public void testGetRoleById() {
        Long id = 1L;
        Role role = new Role("Admin");
        Mockito.when(roleService.getRoleById(id)).thenReturn(role);

        ResponseEntity<Role> response = roleController.getRoleById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin", response.getBody().getName());
    }

    @Test
    public void testUpdateRole() {
        Long id = 1L;
        RoleDTO roleDTO = new RoleDTO("User");
        Role role = new Role("User");
        Mockito.when(roleService.updateRole(id, roleDTO)).thenReturn(role);

        ResponseEntity<Role> response = roleController.updateRole(id, roleDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User", response.getBody().getName());
    }
    
}