package net.codejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.entity.Product;
import net.codejava.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> ListAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void Save(Product product) {
		productRepository.save(product);

	}

	@Override
	public Product BforId(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public void Delete(Long id) {
		productRepository.deleteById(id);
	}

}
