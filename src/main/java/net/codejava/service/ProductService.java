package net.codejava.service;

import java.util.List;
import java.util.Optional;

import net.codejava.entity.Product;

public interface ProductService {

	public List<Product> ListAll();
	//public Optional<Product> ListId(Long id);
	public void Save(Product product);
	public Product BforId(Long id);
	public void Delete(Long id);
}
