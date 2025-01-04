package Form.MyConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
	
	private Form.service.ServiceImpl.CustomUserDetails customUserDetails;
	
	@Bean
	public SecurityFilterChain filtertChain(HttpSecurity http) throws Exception{
		
		http
		.csrf().disable()
		.cors().disable()
		.authorizeRequests()
		.antMatchers("/signin").permitAll()
		.antMatchers("/normal/**").hasRole("NORMAL")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/normal/users");

		return http.build();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception
	{
//		auth.inMemoryAuthentication().withUser("john").password(this.passwordEncoder().encode("935126")).roles("NORMAL");
//		auth.inMemoryAuthentication().withUser("roshni").password(this.passwordEncoder().encode("roshni")).roles("ADMIN");
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(customUserDetails).passwordEncoder(passwordEncoder());
		return null;
		
	}
	
//	@Bean
//	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
//	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	    manager.createUser(User.withUsername("john")
//	      .password(bCryptPasswordEncoder.encode("935126"))
//	      .roles("NORMAL")
//	      .build());
//	    manager.createUser(User.withUsername("roshni")
//	      .password(bCryptPasswordEncoder.encode("roshni"))
//	      .roles("ADMIN")
//	      .build());
//	    return manager;
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(10);
		
	}
	

}
