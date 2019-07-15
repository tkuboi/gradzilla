package net.tkuboi.gradzilla.user;

import net.tkuboi.gradzilla.utils.PasswordEncoderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http:///localhost:4200")
public class UserController {

  private UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping(value = "/authenticate")
  public Boolean authenticate(@RequestParam("name") String name, @RequestParam("password") String password) {
    User user = this.userRepository.findByNameAndPassword(name, password);
    return (user != null);
  }

  @PostMapping(value = "/user/register")
  public Boolean register(@RequestParam("user") User user) {
    if (!user.getEmail().endsWith("@calpoly.edu")) return false;
    if (userRepository.findByName(user.getName()) != null) return false;
    try {
      PasswordEncoder encoder = new PasswordEncoderImpl();
      user.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(user);
    } catch(Exception e) {
      return false;
    }
    return true;
  }

  @GetMapping(value = "/users")
  public List<User> getAll() {
    return userRepository.findAll();
  }
}
