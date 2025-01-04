package Form.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Form.Model.JwtRequest;
import Form.Model.JwtResponse;
import Form.Reposiatry.UserPassReposiatry;
import Form.Services.CustomUserDetailsService;
import Form.healper.JwtUtil;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class JwtController {

	@Autowired
	private UserPassReposiatry userPassReposiatry;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);

		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (UsernameNotFoundException e) {

			e.printStackTrace();
			throw new Exception("Bad Credential");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential");
		}
//		catch(NullPointerException e)
//		{
//			e.printStackTrace();
//			throw new Exception("Null Pointer");
//		}

		// show bad credential

//		return null;

		// use when generate token
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT" + token);

		// {"token":"value"}
		// want to return response
		return ResponseEntity.ok(new JwtResponse(token));

	}
//
//	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
//	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
//		// From the HttpRequest get the claims
//		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
//
//		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
//		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
//		return ResponseEntity.ok(new JwtResponse(token));
//	}
//
//	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
//		Map<String, Object> expectedMap = new HashMap<String, Object>();
//		for (Entry<String, Object> entry : claims.entrySet()) {
//			expectedMap.put(entry.getKey(), entry.getValue());
//		}
//		return expectedMap;
//	}

}
