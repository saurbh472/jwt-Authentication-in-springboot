package Form.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Form.Model.WorkModel;
import Form.Services.SpringService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/normal")
public class HomeController {
	
	@Autowired
	private SpringService springService;
	@GetMapping("/home")
	public String welcome() {
		String text = "this is private page";
		text+="this page is not allowed to unauthorized users";
		return text;
		
	}
	
	@GetMapping("/users")
	public List<WorkModel> getAllUsers()
	{
		
		return this.springService.getAllUsers();
		
	}
	
	@GetMapping("/dom")
	public String dom() {
		
		return "saurbh is here";
		
	}

}
