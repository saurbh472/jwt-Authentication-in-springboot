package com.other.app.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.other.app.Dto.ListofUser;
import com.other.app.Model.JwtRequest;


public interface SpringService {
	public List<ListofUser> getAllUsers();
	public ResponseEntity<?>  deleteUsers(String username,String email, String password);
	public void registerService(JwtRequest jwtRequest);


	
}
