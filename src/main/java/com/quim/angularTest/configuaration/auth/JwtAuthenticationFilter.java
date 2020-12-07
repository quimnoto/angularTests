package com.quim.angularTest.configuaration.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.quim.angularTest.professor.Professor;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public static final String TOKEN_PREFIX = "Bearer ";


  private final TokenProvider tokenProvider;
  private AuthenticationManager authenticationManager;

  /*@Autowired
  public JwtAuthenticationFilter(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }*/
  @Autowired
  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res) throws AuthenticationException {
    ObjectMapper mapper = new ObjectMapper();
    Professor professor = new Professor();
    try {
      professor = mapper.readValue(req.getInputStream(), Professor.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        professor.getLogin(),
        professor.getPassword(),
        new ArrayList<>())
    );

  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
                                          HttpServletResponse res,
                                          FilterChain chain,
                                          Authentication auth) throws IOException {
    String token = tokenProvider.generateToken(auth);
    String header = "Bearer " + token;
    res.addHeader(HttpHeaders.AUTHORIZATION, header);

  }
}
