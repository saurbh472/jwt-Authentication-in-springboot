package Form.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Form.Model.CustomUserDetail;
import Form.Model.JwtRequest;

import Form.Reposiatry.UserPassReposiatry;


@Service
public class CustomUserDetailsService implements UserDetailsService {

//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		
//		if(userName.equals("saurbh"))
//		{
//			return new User("saurbh","psaurbh",new ArrayList<>());
//		}
//		else{
//			throw new UsernameNotFoundException("User not found!!");
//		}
//	
//	}
	
	@Autowired
	private UserPassReposiatry repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		JwtRequest model =this.repository.findByUsername(username);
		
		if(username.equals(model.getUsername()))
		{
			return new CustomUserDetail(model);
		}
		else{
			throw new UsernameNotFoundException("User not found!!");
		}
	}


}
