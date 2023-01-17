package Form.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Form.Model.JwtRequest;
import Form.Model.JwtResponse;
import Form.Reposiatry.UserPassReposiatory;
import Form.helper.JwtUtil;

@RestController
public class JwtController {
	
	@Autowired
	private UserPassReposiatory userPassReposiatry;

	@Autowired
	private Form.Service.CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
		}catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential");
		}
		
		// use when generate token
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);//token generating here
		System.out.println("JWT token generating here" + token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	

}
