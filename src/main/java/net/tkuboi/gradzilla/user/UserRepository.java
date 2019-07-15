package net.tkuboi.gradzilla.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
  User findByNameAndPassword(String name, String password);
  List<User> findAll();
  User findByName(String name);
}
