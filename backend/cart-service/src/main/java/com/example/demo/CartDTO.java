package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
  Long userId;
  Long productId;
  Long quantity;

  public CartDTO(Cart cart) {
    this.userId = cart.getUserId();
    this.productId = cart.getProductId();
    this.quantity = cart.getQuantity();
  }
}
