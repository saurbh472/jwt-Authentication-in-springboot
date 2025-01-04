package Form.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityModel {
	
	@Id
	String username;
	String password;
	String role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public SecurityModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public SecurityModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
