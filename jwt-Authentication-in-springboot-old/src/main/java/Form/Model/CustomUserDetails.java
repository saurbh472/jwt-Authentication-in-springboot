package Form.Model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails{
	
	private JwtRequest jwtRequest;
	
	

	public CustomUserDetails(JwtRequest jwtRequest) {
		super();
		this.jwtRequest = jwtRequest;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> set = new HashSet();
		set.add(new SimpleGrantedAuthority(this.jwtRequest.getRole()));
		return set;
	}

	@Override
	public String getPassword() {
 
		
		return this.jwtRequest.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.jwtRequest.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
