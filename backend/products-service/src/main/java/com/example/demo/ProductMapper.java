package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
  public static com.example.demo.stubs.Product toGrpcProduct(Product entity) {
        return com.example.demo.stubs.Product.newBuilder()
            .setProductId(entity.getProductId())
            .setProductName(entity.getProductName())
            .setProductPrice(entity.getPrice())
            .setProductPictureUrl(entity.getPictureLink())
            .build();
    }

    public static List<com.example.demo.stubs.Product> toGrpcProductList(List<Product> entities) {
        return entities.stream()
            .map(ProductMapper::toGrpcProduct)
            .collect(Collectors.toList());
    }
}
