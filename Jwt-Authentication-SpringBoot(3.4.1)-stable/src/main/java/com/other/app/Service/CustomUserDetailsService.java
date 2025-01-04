package com.other.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.other.app.Model.CustomUserDetail;
import com.other.app.Model.JwtRequest;
import com.other.app.Repository.UserPassReposiatry;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
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
			throw new UsernameNotFoundException("User not found ");
		}
	}


}
