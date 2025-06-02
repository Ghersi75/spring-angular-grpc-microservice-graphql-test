package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  List<User> users;

  public UserRepository() {
    users = new ArrayList<>();

    users.add(new User(0l, "Aldo",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ee/Aldo_Baglio.jpg/120px-Aldo_Baglio.jpg"));
    users.add(new User(1l, "Giovanni",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Giovanni_AGG.jpg/120px-Giovanni_AGG.jpg"));
    users.add(new User(2l, "Giacomo",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Giacomo_Poretti%2C_Savona%2C_2012_cropped.jpg/120px-Giacomo_Poretti%2C_Savona%2C_2012_cropped.jpg"));
  }

  public User getUserByUsername(String username) {
    return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
  }

  public User getUserById(Long userId) {
    return users.stream().filter(user -> user.getUserId() == userId).findFirst().orElse(null);
  }
}