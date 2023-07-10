package br.com.compassuol.pb.challenge.products.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.products.entities.User;
import br.com.compassuol.pb.challenge.products.repositories.UserRepository;
import br.com.compassuol.pb.challenge.products.dtos.UserDTO;
import br.com.compassuol.pb.challenge.products.entities.Role;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleService roleService;

    public User createUser(UserDTO userDTO) {
    	User user = new User();
    	user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        
        Set<Role> roles = new HashSet<>();
        for (Long roleId : userDTO.getRolesIds()) {
        	Role role = roleService.getRoleById(roleId);
            roles.add(role);
        }
        
        user.setRoles(roles);
        
        return userRepository.save(user);
    }
    
    public User getUserById(Long id) {
    	Optional<User> optionalUser = userRepository.findById(id);
    	if(optionalUser.isPresent()) {
    		return optionalUser.get();
    	}
    	throw new RuntimeException("User not found with id " + id);
    }
    
    public User updateUser(Long id, UserDTO userDTO) {
    	User existingUser = getUserById(id);
        if(existingUser == null) {
        	throw new IllegalArgumentException("User not found with id " + id);
        }
        
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        
        Set<Role> roles = new HashSet<>();
        for (Long roleId : userDTO.getRolesIds()) {
        	Role role = roleService.getRoleById(roleId);
            roles.add(role);
        }
        
        existingUser.setRoles(roles);
        
        return userRepository.save(existingUser);
    }
    
}