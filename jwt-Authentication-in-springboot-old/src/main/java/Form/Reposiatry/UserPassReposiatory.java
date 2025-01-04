package Form.Reposiatry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Form.Model.JwtRequest;

@Repository
public interface UserPassReposiatory extends JpaRepository<JwtRequest, String>{
	
	public JwtRequest findByUsername(String username);

}
