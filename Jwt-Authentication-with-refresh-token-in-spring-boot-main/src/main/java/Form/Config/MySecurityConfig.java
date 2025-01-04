package Form.Config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import Form.Services.CustomUserDetailsService;
//import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)

public class MySecurityConfig {
//	implements Filter

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	

	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
		// which api want to auth..
    	 CorsConfiguration corsConfiguration = new CorsConfiguration();
         corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
         corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
         corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
         corsConfiguration.setAllowCredentials(true);
         corsConfiguration.setExposedHeaders(List.of("Authorization"));
         

		http
		.csrf().disable()
		.cors().configurationSource(request -> corsConfiguration).and()
		.authorizeRequests()
		.antMatchers("/token").permitAll()
		.antMatchers("/normal/**").hasRole("NORMAL")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		
		
		//if any exception occurs
		.and().exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		
//		.authenticationEntryPoint(unauthorizedHandler)
		
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); 
		return http.build();
	}
 
    
//	@Bean
//	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
////	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////	    manager.createUser(User.withUsername("john")
////	      .password(bCryptPasswordEncoder.encode("935126"))
////	      .roles("NORMAL")
////	      .build());
////	    manager.createUser(User.withUsername("roshni")
////	      .password(bCryptPasswordEncoder.encode("roshni"))
////	      .roles("ADMIN")
////	      .build());
//		 AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//		AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(null);
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//		return customUserDetailsService;
//
//	   
//	}
	
	 @Bean
	    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	        return authenticationManagerBuilder.build();
	    }
    
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
//		//which authanticATION USE databse or in memory
////		auth.userDetailsService(customUserDetailsService);
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//		
//
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
		
	}




	
	

}
