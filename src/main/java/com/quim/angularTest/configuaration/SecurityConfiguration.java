package com.quim.angularTest.configuaration;

import com.quim.angularTest.configuaration.auth.JwtAuthenticationEntryPoint;
import com.quim.angularTest.configuaration.auth.JwtAuthenticationFilter;
import com.quim.angularTest.configuaration.auth.JwtAuthorizationFilter;
import com.quim.angularTest.configuaration.auth.TokenProvider;
import com.quim.angularTest.configuaration.auth.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /*private final JwtAuthenticationEntryPoint unauthorizedHandler;
   private final JwtAuthenticationFilter authenticationFilter;

   @Autowired
   public SecurityConfiguration(JwtAuthenticationEntryPoint unauthorizedHandler, JwtAuthenticationFilter authenticationFilter) {
       this.unauthorizedHandler = unauthorizedHandler;
       this.authenticationFilter = authenticationFilter;
   }*/
  @Resource(name = "userManagementService")
  private UserDetailsService userDetailsService;

  private TokenProvider tokenProvider;

  public SecurityConfiguration(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }



  @Override
  protected void configure(HttpSecurity http) throws Exception {
   /* http.authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/login").permitAll()
      .antMatchers("/favicon.ico").permitAll()
      .anyRequest().authenticated()
      .and()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
    http
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/favicon.ico").permitAll()
      //.anyRequest().authenticated()
      .and()
      .addFilter(new JwtAuthenticationFilter(authenticationManager(),tokenProvider))
      .addFilter(new JwtAuthorizationFilter(authenticationManager(),tokenProvider))
      // this disables session creation on Spring Security
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/webjars/**", "/**/*.js");
  }

  /*@Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }*/


  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }


}
