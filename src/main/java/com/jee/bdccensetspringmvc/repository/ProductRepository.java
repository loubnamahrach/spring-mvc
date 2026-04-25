package com.jee.bdccensetspringmvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jee.bdccensetspringmvc.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContains(String kw, Pageable pageable);
}
