package com.projedata.backend.controller;

import com.projedata.backend.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.projedata.backend.entity.Product;
import com.projedata.backend.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    List<Product> products = productService.getAll();
    return ResponseEntity.status(HttpStatus.OK).body(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id) {
    if (!productService.existsById(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Product not found");
    }

    Product product = productService.getById(id);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid ProductDto productDto) {
    if (productService.existsByCode(productDto.getCode())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product code already exists");
    }
    if (productService.existsByName(productDto.getName())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product name already exists");
    }

    var newProduct = new Product();
    BeanUtils.copyProperties(productDto, newProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(newProduct));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductDto productDto) {
    if (!productService.existsById(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Product not found");
    }

    var product = productService.getById(id);
    BeanUtils.copyProperties(productDto, product);
    return ResponseEntity.status(HttpStatus.OK).body(productService.update(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    if (!productService.existsById(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Product not found");
    }

    productService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
  }
}
