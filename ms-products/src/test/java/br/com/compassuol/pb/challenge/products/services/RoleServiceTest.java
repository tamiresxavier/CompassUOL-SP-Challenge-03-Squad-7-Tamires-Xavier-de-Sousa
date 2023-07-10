package br.com.compassuol.pb.challenge.products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compassuol.pb.challenge.products.dtos.RoleDTO;
import br.com.compassuol.pb.challenge.products.entities.Role;
import br.com.compassuol.pb.challenge.products.repositories.RoleRepository;

@SpringBootTest
public class RoleServiceTest {
	
	@Mock
	private RoleRepository roleRepository;
	
	@InjectMocks
	private RoleService roleService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateRole() {
		RoleDTO roleDTO = new RoleDTO("Admin");
		Role role = new Role(roleDTO.getName());
		when(roleRepository.save(any(Role.class))).thenReturn(role);
		
		Role createdRole = roleService.createRole(roleDTO);
		
		assertNotNull(createdRole);
		assertEquals(roleDTO.getName(), createdRole.getName());
	}
	
	@Test
	public void testGetRoleById() {
		Long id = 1L;
		Role role = new Role("Admin");
		when(roleRepository.findById(id)).thenReturn(Optional.of(role));
		
		Role returnedRole = roleService.getRoleById(id);
		
		assertNotNull(returnedRole);
		assertEquals(role.getName(), returnedRole.getName());
	}
	
	@Test
	public void testGetRoleByIdNotFound() {
		Long id = 1L;
		when(roleRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class, () -> roleService.getRoleById(id));
	}
	
	@Test
	public void testUpdateRole() {
		Long id = 1L;
		Role existingRole = new Role("Admin");
		RoleDTO roleDTO = new RoleDTO("Super Admin");
		when(roleRepository.findById(id)).thenReturn(Optional.of(existingRole));
		when(roleRepository.save(any(Role.class))).thenReturn(existingRole);
		
		Role updatedRole = roleService.updateRole(id, roleDTO);
		
		assertNotNull(updatedRole);
		assertEquals(roleDTO.getName(), updatedRole.getName());
	}
	
}