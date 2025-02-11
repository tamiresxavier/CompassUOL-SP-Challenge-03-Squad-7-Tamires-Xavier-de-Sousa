package br.com.compassuol.pb.challenge.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.pb.challenge.products.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}