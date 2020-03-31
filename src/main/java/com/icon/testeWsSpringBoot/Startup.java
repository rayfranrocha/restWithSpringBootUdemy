package com.icon.testeWsSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		BCryptPasswordEncoder bb = new BCryptPasswordEncoder(16);
		String r = bb.encode("123");
		System.out.println(">> my hash: ["+r+"]");
	}

}
