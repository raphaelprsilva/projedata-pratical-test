package com.projedata.backend.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
  @NotBlank
  private String code;
  @NotBlank
  private String name;
  @DecimalMin("0.01") @NotNull
  private double price;
}
