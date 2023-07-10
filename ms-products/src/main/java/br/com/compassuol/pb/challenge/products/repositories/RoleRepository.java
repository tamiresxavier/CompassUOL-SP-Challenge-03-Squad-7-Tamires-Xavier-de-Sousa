package br.com.compassuol.pb.challenge.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.pb.challenge.products.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}