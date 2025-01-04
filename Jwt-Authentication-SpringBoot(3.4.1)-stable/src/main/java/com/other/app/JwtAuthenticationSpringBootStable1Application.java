package com.other.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.other.app.Model.JwtRequest;
import com.other.app.Repository.UserPassReposiatry;




@SpringBootApplication
public class JwtAuthenticationSpringBootStable1Application implements CommandLineRunner {
	
	@Autowired
	private UserPassReposiatry repository;
	  @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationSpringBootStable1Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		if(repository.findUserInitialService()==0) {
		JwtRequest model = new JwtRequest();
		model.setUsername("admin");
		model.setPassword(this.bCryptPasswordEncoder.encode("admin"));
		model.setRole("ROLE_NORMAL");
		model.setEmail("admin@adminxyzmail.com");
		this.repository.save(model);
		}

	}
}
