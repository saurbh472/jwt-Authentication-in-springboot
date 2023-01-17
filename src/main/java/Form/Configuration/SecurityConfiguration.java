package Form.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import Form.Service.CustomUserDetailsService;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	
	//Configuration about roles and token and exception handling in the code
	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception 
	{
		http
		    .cors().disable()
		    .csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/token").permitAll()
		    .antMatchers("/chat/**").hasRole("ADMIN")
		    .antMatchers("/car/**").hasRole("NORMAL")
		    .anyRequest()
		    .authenticated()
		    .and()
			//if any exception occurs
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
	        .and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); 
		return http.build();
	}
	
	//user password authentication through database 
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	       AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);//12
	        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	        return authenticationManagerBuilder.build();
	}
	
	//convert password into bcrypt encryption
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
