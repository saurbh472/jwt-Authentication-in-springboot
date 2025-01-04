package Form.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Form.Model.CustomUserDetails;
import Form.Model.JwtRequest;
import Form.Reposiatry.UserPassReposiatory;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserPassReposiatory userPassReposiatory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtRequest object =  userPassReposiatory.findByUsername(username);
		
		if(username.equals(object.getUsername()))
		{
			return new CustomUserDetails(object);
		}
		else {
			throw new UsernameNotFoundException("Username not found!");
		}
		
	}

}
