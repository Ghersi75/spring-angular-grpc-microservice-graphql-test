package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import com.example.demo.stubs.GetUserByIdRequest;
import com.example.demo.stubs.GetUserByUsernameRequest;
import com.example.demo.stubs.UserResponse;
import com.example.demo.stubs.UserServiceGrpc.UserServiceImplBase;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

@GrpcService
public class UserService extends UserServiceImplBase {
  @Autowired
  UserRepository userRepository;

  @Override
  public void getUserById(GetUserByIdRequest request,
      StreamObserver<UserResponse> responseObserver) {
    User res = userRepository.getUserById(request.getUserId());

    UserResponse response = UserResponse.newBuilder()
        .setUserId(res.getUserId())
        .setUsername(res.getUsername())
        .setProfilePictureUrl(res.profilePictureLink)
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getUserByUsername(GetUserByUsernameRequest request,
      StreamObserver<UserResponse> responseObserver) {
    User res = userRepository.getUserByUsername(request.getUsername());
    UserResponse response;
    if (res == null) {
      responseObserver.onError(Status.NOT_FOUND
          .withDescription("User not found")
          .asRuntimeException());
    } else {
      response = UserResponse.newBuilder()
          .setUserId(res.getUserId())
          .setUsername(res.getUsername())
          .setProfilePictureUrl(res.profilePictureLink)
          .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

  }
}
