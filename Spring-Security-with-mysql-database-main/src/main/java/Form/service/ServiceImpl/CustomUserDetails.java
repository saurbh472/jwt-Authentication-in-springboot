package Form.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Form.Model.SecurityModel;
import Form.Reposiatry.SecurityReposiatry;

@Service
public class CustomUserDetails implements UserDetailsService{
	
	@Autowired
	private SecurityReposiatry reposiatry;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityModel securityModel=  reposiatry.findByUsername(username);
	if(username.equals(securityModel.getUsername()))
	{
		return new Form.Model.CustomUserDetails(securityModel);
	}
	else {
		 throw new UsernameNotFoundException("Not Found user Bad Credential");
	}
	}

}
