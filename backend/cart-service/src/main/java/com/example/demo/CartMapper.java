package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.stubs.CartItem;

public class CartMapper {

    public static CartItem toGrpcCartItem(Cart cart) {
        return CartItem.newBuilder()
            .setProductId(cart.getProductId())
            .setQuantity(cart.getQuantity())
            .build();
    }

    public static List<CartItem> toGrpcCartItemList(List<Cart> cartList) {
        return cartList.stream()
            .map(CartMapper::toGrpcCartItem)
            .collect(Collectors.toList());
    }
}
