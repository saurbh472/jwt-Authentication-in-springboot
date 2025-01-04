package com.other.app.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.other.app.Dto.ListofUser;
import com.other.app.Model.JwtRequest;
import com.other.app.Repository.UserPassReposiatry;
import com.other.app.Service.SpringService;

@Service
public class SpringServiceImpl implements SpringService{

	@Autowired
	private UserPassReposiatry repository;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserPassReposiatry userRepository;
	

	@Override
	public void registerService(JwtRequest jwtRequest) {

		JwtRequest model = new JwtRequest();
		model.setUsername(jwtRequest.getUsername());
		model.setPassword(this.bCryptPasswordEncoder.encode(jwtRequest.getPassword()));
		model.setRole(jwtRequest.getRole());
		this.repository.save(model);
	}
	

	@Override
	public List<ListofUser> getAllUsers() {
		List<JwtRequest> jwtRequests= repository.findUser();
		List<ListofUser> listofUserList = new ArrayList<>();
		for (JwtRequest jwtRequest : jwtRequests) {
			ListofUser listofUser = new ListofUser();
			listofUser.setUsername(jwtRequest.getUsername());
			listofUser.setRole(jwtRequest.getRole());
			listofUserList.add(listofUser);                                                                
			
		}
		return listofUserList;
		
		
	}


	@Override
	public ResponseEntity<?>  deleteUsers(String username, String email, String password) {
		   JwtRequest user = userRepository.findByUsername(username);
		 if(user.getUsername().equals(username)) {
		        // Check if the old password matches
		        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password is incorrect");
		        }
	        	userRepository.deleteuser(username,email);
	}
		return null;
		 
	}
}
