package br.com.compassuol.pb.challenge.products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compassuol.pb.challenge.products.dtos.UserDTO;
import br.com.compassuol.pb.challenge.products.entities.Role;
import br.com.compassuol.pb.challenge.products.entities.User;
import br.com.compassuol.pb.challenge.products.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleService roleService;
	
	@InjectMocks
	private UserService userService;
	
	private User user;
	private UserDTO userDTO;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		user = new User("Tamires", "Xavier", "tamires.xavier@gmail.com", "tamiresx7*");
		
		Role role = new Role();
		role.setId(1L);
		role.setName("ROLE_USER");
		
		user.getRoles().add(role);
		
		userDTO = new UserDTO();
		userDTO.setFirstName("John");
		userDTO.setLastName("Doe");
		userDTO.setEmail("john.doe@example.com");
		userDTO.setPassword("password");
		Set<Long> rolesIds = new HashSet<>();
		rolesIds.add(1L);
		userDTO.setRolesIds(rolesIds);
	}
	
	@Test
	public void testCreateUser() {
		when(roleService.getRoleById(anyLong())).thenReturn(user.getRoles().iterator().next());
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		User createdUser = userService.createUser(userDTO);
		
		assertEquals(user.getFirstName(), createdUser.getFirstName());
		assertEquals(user.getLastName(), createdUser.getLastName());
		assertEquals(user.getEmail(), createdUser.getEmail());
		assertEquals(user.getPassword(), createdUser.getPassword());
		assertEquals(user.getRoles(), createdUser.getRoles());
	}
	
	@Test
	public void testGetUserById() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		
		User retrievedUser = userService.getUserById(1L);
		
		assertEquals(user.getFirstName(), retrievedUser.getFirstName());
		assertEquals(user.getLastName(), retrievedUser.getLastName());
		assertEquals(user.getEmail(), retrievedUser.getEmail());
		assertEquals(user.getPassword(), retrievedUser.getPassword());
		assertEquals(user.getRoles(), retrievedUser.getRoles());
	}
	
	@Test
	public void testGetUserByIdNotFound() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class, () -> {
			userService.getUserById(1L);
		});
	}
	
	@Test
	public void testUpdateUser() {
		when(roleService.getRoleById(anyLong())).thenReturn(user.getRoles().iterator().next());
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		User updatedUser = userService.updateUser(1L, userDTO);
		
		assertEquals(user.getFirstName(), updatedUser.getFirstName());
		assertEquals(user.getLastName(), updatedUser.getLastName());
		assertEquals(user.getEmail(), updatedUser.getEmail());
		assertEquals(user.getPassword(), updatedUser.getPassword());
		assertEquals(user.getRoles(), updatedUser.getRoles());
	}

}