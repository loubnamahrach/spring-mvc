package com.jee.bdccensetspringmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jee.bdccensetspringmvc.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
