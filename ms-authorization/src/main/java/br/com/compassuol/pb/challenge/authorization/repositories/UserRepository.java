package br.com.compassuol.pb.challenge.authorization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compassuol.pb.challenge.authorization.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}