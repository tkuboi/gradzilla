package net.tkuboi.gradzilla.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authException) throws IOException {
    System.out.println("Unauthorized!");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    PrintWriter pw = response.getWriter();
    pw.print("{\"message\":\"User Credential is missing or incorrect.\"}");
    pw.flush();
    pw.close();
    //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
    //  "Unauthorized");
  }
}