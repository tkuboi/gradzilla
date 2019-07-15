package net.tkuboi.gradzilla.configuration;

import net.tkuboi.gradzilla.utils.PasswordEncoderImpl;
import net.tkuboi.gradzilla.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  @Autowired
  private AuthenticationSuccessHandler successHandler;
  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
      .antMatchers("/courses/**").permitAll()
      .and().authorizeRequests().antMatchers("/**").authenticated()
      .and().formLogin()
      .successHandler(successHandler)
      .failureHandler(new SimpleUrlAuthenticationFailureHandler())
      .and().logout()
      .and().csrf().disable()
      .exceptionHandling()
      .authenticationEntryPoint(restAuthenticationEntryPoint);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoderImpl());
  }
}
