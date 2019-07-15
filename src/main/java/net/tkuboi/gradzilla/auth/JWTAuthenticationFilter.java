package net.tkuboi.gradzilla.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.tkuboi.gradzilla.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static net.tkuboi.gradzilla.auth.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request,
                                              javax.servlet.http.HttpServletResponse response)
    throws AuthenticationException {
    try {
      User creds = new ObjectMapper()
        .readValue(request.getInputStream(), User.class);
      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          creds.getName(),
          creds.getPassword(),
          new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(javax.servlet.http.HttpServletRequest request,
                                          javax.servlet.http.HttpServletResponse response,
                                          javax.servlet.FilterChain chain,
                                          Authentication authResult)
    throws java.io.IOException,
    javax.servlet.ServletException {
    String token = Jwts.builder()
      .setSubject((
        (org.springframework.security.core.userdetails.User)
          authResult.getPrincipal()).getUsername())
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact();
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
