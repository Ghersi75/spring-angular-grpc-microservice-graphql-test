package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import com.example.demo.stubs.AddToCartRequest;
import com.example.demo.stubs.EmptyResponse;
import com.example.demo.stubs.GetCartByUserIdRequest;
import com.example.demo.stubs.GetCartByUserIdResponse;
import com.example.demo.stubs.CartServiceGrpc.CartServiceImplBase;

import io.grpc.stub.StreamObserver;

@GrpcService
public class CartService extends CartServiceImplBase {
  @Autowired
  CartRepository cartRepository;

  @Override
  public void getCartByUserId(GetCartByUserIdRequest request,
      StreamObserver<GetCartByUserIdResponse> responseObserver) {
    List<Cart> result = this.cartRepository.findByUserId(request.getUserId());
    
    GetCartByUserIdResponse response = GetCartByUserIdResponse.newBuilder().addAllCartItems(CartMapper.toGrpcCartItemList(result)).build();
    
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void addToCart(AddToCartRequest request, StreamObserver<EmptyResponse> responseObserver) {
    Cart newCart = new Cart();
    newCart.setUserId(request.getUserId());
    newCart.setProductId(request.getProductId());
    newCart.setQuantity(request.getQuantity());
    this.cartRepository.save(newCart);

    responseObserver.onNext(null);
    responseObserver.onCompleted();
  }
}
