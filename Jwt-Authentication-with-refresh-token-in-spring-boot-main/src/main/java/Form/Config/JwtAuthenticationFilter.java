package Form.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import Form.Services.CustomUserDetailsService;
import Form.healper.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
@CrossOrigin(origins = "http://localhost:3000")
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get jwt
		// Bearer
    	//validate
		System.out.println("request Accepted from frontend....!");
		
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String JwtToken = null;

		
			// null and formate
		try {
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				JwtToken = requestTokenHeader.substring(7);
//				try {
					username = this.jwtUtil.getUsernameFromToken(JwtToken);
					System.out.println(username);

//				}
//			catch (Exception e) {
//					e.printStackTrace();
//
//				}
		
				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

				// security
//				if (StringUtils.hasText(JwtToken) && jwtUtil.validateToken(JwtToken) ) 
//		{
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
							}
				 else {
					System.out.println("Token is not validated.."); 
				}
			}

			}catch (ExpiredJwtException ex) {
			
//			String isRefreshToken = request.getHeader("isRefreshToken");
//			String requestURL = request.getRequestURL().toString();
//			// allow for Refresh Token creation if following conditions are true.
//			if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
//				allowForRefreshToken(ex, request);
//			} else
				request.setAttribute("exception", ex);
		
		} catch (BadCredentialsException ex) {
			request.setAttribute("exception", ex);
			throw ex;
		}
		catch (Exception ex) {
			
			System.out.println(ex);
		}
		System.out.println("complete 1....!");
		filterChain.doFilter(request, response);
		System.out.println("complete 2....!");

	}
	

//	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
//
//		// create a UsernamePasswordAuthenticationToken with null values.
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//				null, null, null);
//		// After setting the Authentication in the context, we specify
//		// that the current user is authenticated. So it passes the
//		// Spring Security Configurations successfully.
//		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//		// Set the claims so that in controller we will be using it to create
//		// new JWT
//		request.setAttribute("claims", ex.getClaims());
//
//	}

	}

//
//	private String extractJwtFromRequest(HttpServletRequest request) {
//		String bearerToken = request.getHeader("Authorization");
//		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//			return bearerToken.substring(7, bearerToken.length());
//		}
//		return null;
//	}
//	
//}
