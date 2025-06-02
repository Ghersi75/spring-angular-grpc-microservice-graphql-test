package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import com.example.demo.stubs.GetAllProductsRequest;
import com.example.demo.stubs.GetAllProductsResponse;
import com.example.demo.stubs.GetProductByIdRequest;
import com.example.demo.stubs.ProductServiceGrpc.ProductServiceImplBase;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ProductService extends ProductServiceImplBase {
  @Autowired
  ProductRepository productRepository;

  @Override
  public void getAllProducts(GetAllProductsRequest request, StreamObserver<GetAllProductsResponse> responseObserver) {
    GetAllProductsResponse response = GetAllProductsResponse.newBuilder()
        .addAllProducts(ProductMapper.toGrpcProductList(productRepository.getAllProducts()))
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getProductById(GetProductByIdRequest request, StreamObserver<com.example.demo.stubs.Product> responseObserver) {
    Product product = productRepository.getProductById(request.getProductId());

    if (product == null) {
      responseObserver.onError(Status.NOT_FOUND
          .withDescription("User not found")
          .asRuntimeException());
    } else {
      com.example.demo.stubs.Product response = com.example.demo.stubs.Product.newBuilder()
          .setProductId(product.getProductId())
          .setProductName(product.getProductName())
          .setProductPrice(product.getPrice())
          .setProductPictureUrl(product.getPictureLink())
          .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

  }

}
