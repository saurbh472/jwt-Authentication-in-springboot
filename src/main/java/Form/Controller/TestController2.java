package Form.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class TestController2 {

	@GetMapping("/supra")
	public String data() {
		return "Hii!This is Supra!";
	}
}
