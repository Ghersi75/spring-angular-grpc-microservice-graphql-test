package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByUserIdAndProductId(Long userId, Long productId);
  List<Cart> findByProductId(Long productId);
  List<Cart> findByUserId(Long userId);
}
