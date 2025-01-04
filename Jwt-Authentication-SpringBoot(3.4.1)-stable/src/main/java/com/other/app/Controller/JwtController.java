package com.other.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.Dto.ListofUser;
import com.other.app.Model.AuthResponse;
import com.other.app.Model.ChangePasswordRequest;
import com.other.app.Model.JwtRequest;
import com.other.app.Model.RefreshTokenRequest;
import com.other.app.Repository.UserPassReposiatry;
import com.other.app.Service.CustomUserDetailsService;
import com.other.app.Service.SpringService;
import com.other.app.helper.JwtUtil;


@CrossOrigin
@RestController
public class JwtController {

//	@Autowired
//	private UserPassReposiatry userPassReposiatry;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	SpringService springService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserPassReposiatry userRepository;
	
	@PostMapping("/register_userDetails")
	public void Registration(@RequestBody JwtRequest jwtRequest)
	{
		springService.registerService(jwtRequest);
	}
	
	 	@PostMapping("/token")
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
	            );
	        } catch (Exception e) {
	            throw new Exception("Incorrect username or password", e);
	        }
	        
	        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

	        final String accessToken = jwtUtil.generateToken(userDetails);
	        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);

	        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
	    }
	 
	 @PostMapping("/refreshtoken")
	    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws Exception {
	        String refreshToken = refreshTokenRequest.getRefreshToken();
	        try {
	            String username = jwtUtil.extractUsername(refreshToken);
	            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
	            if (jwtUtil.validateToken(refreshToken, userDetails)) {
	                String accessToken = jwtUtil.generateToken(userDetails);
	                return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
	            }
	        } catch (Exception e) {
	            throw new BadCredentialsException("Invalid refresh token");
	        }
	        throw new BadCredentialsException("Invalid refresh token");
	    }

	 
	 	@PostMapping("/change-password")
	    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		 
		   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	 		if(changePasswordRequest.getNewPassword().equals(changePasswordRequest.getRetyepassword())) {
		   JwtRequest user = userRepository.findByUsername(changePasswordRequest.getUsername());
		   
		   if(user.getUsername().equals(changePasswordRequest.getUsername()) && userDetails.getUsername().equals(changePasswordRequest.getUsername())) {
	        // Check if the old password matches
	        if (!bCryptPasswordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password is incorrect");
	        }
	        // Update the password
	        user.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
	        userRepository.save(user);

	        return ResponseEntity.ok("Password changed successfully");
	    }
		
//		}
	 		return null;
	 	}
	 	@GetMapping("/userdetails")
	 	public List<ListofUser> returnUserList(){
	 		return springService.getAllUsers();
	 	}
	 	
	 	@DeleteMapping("/deleteuser/username={username}/email={email}/password={password}")
	 	public ResponseEntity<?>  returnUserList(@PathVariable("username") String username,@PathVariable("email") String email, @PathVariable("password") String password){
	 		return springService.deleteUsers(username,email, password);
	 	}
}