package com.quim.angularTest.configuaration.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  public static final String TOKEN_PREFIX = "Bearer ";
  private final TokenProvider tokenProvider;

  public JwtAuthorizationFilter(AuthenticationManager authManager, TokenProvider tokenProvider) {
    super(authManager);
    this.tokenProvider = tokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
                                  HttpServletResponse res,
                                  FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HttpHeaders.AUTHORIZATION);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  // Reads the JWT from the Authorization header, and then uses JWT to validate the token
  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (token != null) {
      // parse the token.
      String authToken = token.replace(TOKEN_PREFIX, "");
      Optional<String> user = tokenProvider.getUsernameFromToken(authToken);

      if (user.isPresent()) {
        // new arraylist means authorities
        return new UsernamePasswordAuthenticationToken(user.get(), null, new ArrayList<>());
      }

      return null;
    }

    return null;
  }
}
