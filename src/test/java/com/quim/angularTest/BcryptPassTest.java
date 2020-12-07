package com.quim.angularTest;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class BcryptPassTest {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  void createPass() {
    String encyptPass = passwordEncoder.encode("password");
    System.out.println(encyptPass);
  }
}
