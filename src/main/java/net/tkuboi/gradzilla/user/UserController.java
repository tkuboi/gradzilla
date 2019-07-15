package net.tkuboi.gradzilla.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping(value = "/authenticate")
  @CrossOrigin(origins = "http:///localhost:4200")
  public Boolean authenticate(@RequestParam("name") String name, @RequestParam("password") String password) {
    User user = this.userRepository.findByNameAndPassword(name, password);
    return (user != null);
  }
}
