package com.apirest.test.demoapirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.test.demoapirest.Entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
