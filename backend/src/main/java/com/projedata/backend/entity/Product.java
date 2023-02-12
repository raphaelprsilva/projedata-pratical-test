package com.projedata.backend.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private String code;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private double price;
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

}
