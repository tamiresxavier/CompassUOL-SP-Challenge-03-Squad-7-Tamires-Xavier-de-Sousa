package br.com.compassuol.pb.challenge.products.services;

import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.products.dtos.RoleDTO;
import br.com.compassuol.pb.challenge.products.entities.Role;
import br.com.compassuol.pb.challenge.products.repositories.RoleRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(RoleDTO roleDTO) {
    	Role role = new Role();
    	role.setName(roleDTO.getName());
        
        return roleRepository.save(role);
    }
    
    public Role getRoleById(Long id) {
    	Optional<Role> optionalRole = roleRepository.findById(id);
    	if(optionalRole.isPresent()) {
    		return optionalRole.get();
    	}
    	throw new RuntimeException("Role not found with id " + id);
    }
    
    public Role updateRole(Long id, RoleDTO roleDTO) {
    	Role existingRole = getRoleById(id);
        if(existingRole == null) {
        	throw new IllegalArgumentException("Role not found with id " + id);
        }
        
        existingRole.setName(roleDTO.getName());
        
        return roleRepository.save(existingRole);
    }
    
}