package com.madhu.ProductView.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.madhu.ProductView.model.ProductView;



public interface ProductViewRepository extends JpaRepository<ProductView, Integer> {
	
	}