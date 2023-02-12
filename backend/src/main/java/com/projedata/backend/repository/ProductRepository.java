package com.projedata.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projedata.backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsByCode(String code);
  boolean existsById(Long id);
  boolean existsByName(String name);
}
