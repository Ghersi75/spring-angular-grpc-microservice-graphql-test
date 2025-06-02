package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
  List<Product> products;
  public ProductRepository() {
    products = new ArrayList<>();
    products.add(new Product(0, "Shirt", 999, "https://www.uberprints.com/content/products/flat/800x800/gig500_1_wht.jpg"));
    products.add(new Product(1, "Pants", 3999, "https://us.strauss.com/cdn/shop/files/Work-pants-e.s.motion-ten_-women-USA-oxidblack.jpg?v=1719322758"));
    products.add(new Product(2, "Jacket", 9999, "https://m.media-amazon.com/images/I/71E7c09iTdL._AC_UY1000_.jpg"));
  }

  public List<Product> getAllProducts() {
    return this.products;
  }

  public Product getProductById(long id) {
    return this.products.stream().filter(p -> p.productId == id).findFirst().orElse(null);
  }
}
