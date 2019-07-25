package net.tkuboi.gradzilla.user;

import net.tkuboi.gradzilla.enrollment.Enrollment;
import net.tkuboi.gradzilla.enrollment.EnrollmentRepository;
import net.tkuboi.gradzilla.utils.PasswordEncoderImpl;
import net.tkuboi.gradzilla.utils.RestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http:///localhost:4200")
public class UserController {

  private final UserRepository userRepository;
  private final EnrollmentRepository enrollmentRepository;

  @Autowired
  public UserController(UserRepository userRepository, EnrollmentRepository enrollmentRepository) {
    this.userRepository = userRepository;
    this.enrollmentRepository = enrollmentRepository;
  }

  @PostMapping(value = "/users/register")
  public RestResponseDto registerUser(@RequestBody UserDto userDto) {

    if (!userDto.getEmail().endsWith("@calpoly.edu")) {
      return new RestResponseDto(1,
        "The email address has to be calpoly email address.",
        RestResponseDto.Status.ERROR.name(), null);
    }
    if (userRepository.findByName(userDto.getUsername()) != null) {
      return new RestResponseDto(1,
        "The username already exists!",
        RestResponseDto.Status.ERROR.name(), null);
    }
    User user = new User(
      userDto.getUsername(), userDto.getEmail(), userDto.getFirst(), userDto.getLast(),
      null, User.Role.STUDENT.name(), false);
    Enrollment enrollment = new Enrollment(
      userDto.getUsername(), userDto.getCourse(),
      userDto.getSection(), userDto.getYear(), userDto.getQuarter());
    try {
      //PasswordEncoder encoder = new PasswordEncoderImpl();
      //user.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(user);
      enrollmentRepository.save(enrollment);
    } catch(Exception e) {
      return new RestResponseDto(1,
        e.getMessage(),
        RestResponseDto.Status.ERROR.name(), null);
    }
    return new RestResponseDto(0,
      "Check the email sent to your address!",
      RestResponseDto.Status.SUCCESS.name(), user);
  }

  @PostMapping(value = "/users/put")
  public RestResponseDto putUser(@RequestBody UserDto userDto) {

    if (userRepository.findByName(userDto.getUsername()) != null) {
      return new RestResponseDto(1,
        "The username already exists!",
        RestResponseDto.Status.ERROR.name(), null);
    }

    User user = new User(
      userDto.getUsername(), userDto.getEmail(), userDto.getFirst(),
      userDto.getLast(), null, User.Role.STUDENT.name(), false);
    Enrollment enrollment = new Enrollment(
      userDto.getUsername(), userDto.getCourse(), userDto.getSection(),
      userDto.getYear(), userDto.getQuarter());
    try {
      PasswordEncoder encoder = new PasswordEncoderImpl();
      user.setPassword(encoder.encode(userDto.getPassword()));
      userRepository.save(user);
      enrollmentRepository.save(enrollment);
    } catch(Exception e) {
      return new RestResponseDto(1,
        e.getMessage(),
        RestResponseDto.Status.ERROR.name(), null);
    }
    return new RestResponseDto(0,
      "Check the email sent to your address!",
      RestResponseDto.Status.SUCCESS.name(), user);
  }

  @GetMapping(value = "/users")
  public List<User> getAll() {
    return userRepository.findAll();
  }
}
