package Form.Reposiatry;

import org.springframework.data.jpa.repository.JpaRepository;

import Form.Model.SecurityModel;

public interface SecurityReposiatry extends JpaRepository<SecurityModel, String>{

	public SecurityModel findByUsername(String username);
}
