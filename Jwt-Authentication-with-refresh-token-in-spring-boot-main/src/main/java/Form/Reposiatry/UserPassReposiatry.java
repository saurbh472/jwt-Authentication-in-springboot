package Form.Reposiatry;

import org.springframework.data.jpa.repository.JpaRepository;

import Form.Model.JwtRequest;


public interface UserPassReposiatry extends JpaRepository<JwtRequest, String>{
	public JwtRequest findByUsername(String username);
}
