package net.tkuboi.gradzilla.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static net.tkuboi.gradzilla.auth.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  public JWTAuthorizationFilter(AuthenticationManager authManager) {
    super(authManager);
  }

  @Override
  protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
                                  javax.servlet.http.HttpServletResponse response,
                                  javax.servlet.FilterChain chain)
    throws java.io.IOException, javax.servlet.ServletException {

    String header = request.getHeader(HEADER_STRING);
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      // parse the token.
      String user = Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
      }
      return null;
    }
    return null;
  }
}
