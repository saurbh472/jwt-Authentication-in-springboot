package Form;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Form.Model.JwtRequest;

import Form.Reposiatry.UserPassReposiatry;


@SpringBootApplication
public class JwtAuthenticationProjectApplication implements CommandLineRunner{

	@Autowired
	private UserPassReposiatry repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JwtRequest model = new JwtRequest();
		model.setUsername("saurbh");
		model.setPassword(this.bCryptPasswordEncoder.encode("saurbh"));
		model.setRole("ROLE_NORMAL");
		this.repository.save(model);

		JwtRequest model1 = new JwtRequest();
		model1.setUsername("roshni");
		model1.setPassword(this.bCryptPasswordEncoder.encode("roshni"));
		model1.setRole("ROLE_ADMIN");
		this.repository.save(model1);

	}

}
