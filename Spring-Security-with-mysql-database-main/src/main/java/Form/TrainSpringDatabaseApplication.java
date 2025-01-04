package Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Form.Model.SecurityModel;
import Form.Reposiatry.SecurityReposiatry;

@SpringBootApplication
public class TrainSpringDatabaseApplication implements CommandLineRunner{

	@Autowired
	private SecurityReposiatry repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(TrainSpringDatabaseApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		SecurityModel model = new SecurityModel();
		model.setUsername("user");
		model.setPassword(this.bCryptPasswordEncoder.encode("user"));
		model.setRole("ROLE_NORMAL");
		this.repository.save(model);

		SecurityModel model1 = new SecurityModel();
		model1.setUsername("admin");
		model1.setPassword(this.bCryptPasswordEncoder.encode("admin"));
		model1.setRole("ROLE_ADMIN");
		this.repository.save(model1);

	}

}
