package net.codejava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.codejava.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
