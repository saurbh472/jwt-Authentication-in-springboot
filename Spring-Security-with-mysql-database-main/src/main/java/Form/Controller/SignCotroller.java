package Form.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignCotroller {
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
}
