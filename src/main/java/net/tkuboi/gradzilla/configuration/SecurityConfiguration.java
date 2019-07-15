package net.tkuboi.gradzilla.configuration;

import net.tkuboi.gradzilla.auth.AuthenticationSuccessHandler;
import net.tkuboi.gradzilla.auth.JWTAuthenticationFilter;
import net.tkuboi.gradzilla.auth.JWTAuthorizationFilter;
import net.tkuboi.gradzilla.auth.RestAuthenticationEntryPoint;
import net.tkuboi.gradzilla.utils.PasswordEncoderImpl;
import net.tkuboi.gradzilla.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userDetailsService;
  private final AuthenticationSuccessHandler successHandler;
  private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Autowired
  public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                               AuthenticationSuccessHandler successHandler,
                               RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
    this.userDetailsService = userDetailsService;
    this.successHandler = successHandler;
    this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
      .antMatchers("/user/register").permitAll()
      .and().authorizeRequests().antMatchers("/**").authenticated()
      //.and().formLogin()
      //.successHandler(successHandler)
      //.failureHandler(new SimpleUrlAuthenticationFailureHandler())
      .and().logout()
      .and().csrf().disable()
      .exceptionHandling()
      .authenticationEntryPoint(restAuthenticationEntryPoint)
      .and()
      .addFilter(new JWTAuthenticationFilter(authenticationManager()))
      .addFilter(new JWTAuthorizationFilter(authenticationManager()));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoderImpl());
  }
}
