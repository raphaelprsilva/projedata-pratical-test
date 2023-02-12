package com.projedata.backend.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.projedata.backend.repository.ProductRepository;
import com.projedata.backend.entity.Product;

import javax.transaction.Transactional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
      this.productRepository = productRepository;
  }

  public List<Product> getAll() {
    return productRepository.findAll();
  }

  public Product getById(Long id) {
    return productRepository.findById(id).get();
  }

  @Transactional
  public Product create(Product product) {
    product.setCreated(new java.util.Date());
    return productRepository.saveAndFlush(product);
  }

  public Product update(Product product) {
    product.setCreated(product.getCreated());
    product.setUpdated(new java.util.Date());
    return productRepository.saveAndFlush(product);
  }

  @Transactional
  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new RuntimeException("Product not found");
    }

    Product product = productRepository.findById(id).get();
    productRepository.delete(product);
  }

  public boolean existsByCode(String code) {
    return productRepository.existsByCode(code);
  }

  public boolean existsByName(String name) {
    return productRepository.existsByName(name);
  }

  public boolean existsById(Long id) {
    return productRepository.existsById(id);
  }
}
