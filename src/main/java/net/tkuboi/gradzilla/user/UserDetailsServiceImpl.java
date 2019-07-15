package net.tkuboi.gradzilla.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = this.userRepository.findById(s).get();

    return new org.springframework.security.core.userdetails.User(
      user.getName(), user.getPassword(),
      user.isActive(), user.isActive(), user.isActive(), user.isActive(),
     getAuthorities(user)
    );
  }

  private Collection<GrantedAuthority> getAuthorities(User user) {
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(User.Role.STUDENT.name()));
    if (user.getRole().equals(User.Role.ADMIN.name())) {
      authorities.add(new SimpleGrantedAuthority(User.Role.ADMIN.name()));
    }
    return authorities;
  }
}
