package com.quim.angularTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class AngularTestApplication {

	public static void main(String[] args) {
	  SpringApplication.run(AngularTestApplication.class, args);
	}

}
