package Form.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TestController {
	
	@GetMapping("/show")
	public String show()
	{
		return "Hello I'm Niral chatbot! what is your truly desire";
	}

}
