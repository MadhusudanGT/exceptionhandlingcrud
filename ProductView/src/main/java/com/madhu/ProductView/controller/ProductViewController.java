package com.madhu.ProductView.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.madhu.ProductView.exception.ResourceNotFoundException;
import com.madhu.ProductView.model.ProductView;
import com.madhu.ProductView.repository.ProductViewRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductViewController {
	@Autowired
	ProductViewRepository repository;

	@GetMapping("/productlists")
	public List<ProductView> getAllProducts() {
		return repository.findAll();
	}

	@GetMapping("/productlists/{id}")
	public ResponseEntity<ProductView> getProductById(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		ProductView employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/productlist")
	public ProductView createProdcut (@RequestBody ProductView product) {
		return repository.save(product);
	}

	@PutMapping("/productlist/{id}")
	public ResponseEntity<ProductView> updateProduct(@PathVariable(value = "id") int id,
			 @RequestBody ProductView prod) throws ResourceNotFoundException {
		ProductView product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Productnot found for this id :: " + id));

		product.setName(prod.getName());
		product.setPrice(prod.getPrice());
		product.setDescription(prod.getDescription());
		product.setDiscount(prod.getDiscount());
		product.setCategory_id(prod.getCategory_id());
		product.setBrand(prod.getBrand());
		product.setExp_date(prod.getExp_date());
		product.setMfd_date(prod.getMfd_date());
		product.setStart_date(prod.getStart_date());
		product.setEnd_date(prod.getEnd_date());
		
		final ProductView updated = repository.save(product);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/productlist/{id}")
	public Map<String, Boolean> deleteProdcut(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		ProductView product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

		repository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	
}
}