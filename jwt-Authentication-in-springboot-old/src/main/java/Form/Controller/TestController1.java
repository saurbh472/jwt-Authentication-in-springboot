package Form.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class TestController1 {
	
	@GetMapping("/bot")
	public String data() {
		return "Hii!This is chatBot!";
	}

}
