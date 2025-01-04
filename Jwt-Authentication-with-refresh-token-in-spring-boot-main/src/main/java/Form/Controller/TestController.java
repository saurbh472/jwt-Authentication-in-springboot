package Form.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Form.Model.WorkModel;
import Form.Services.SpringService1;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class TestController {

	@Autowired
	private SpringService1 springService1;
	@GetMapping("/home")
	public String home()
	{
		return "Hello brother im spring home";
	}
	
	@GetMapping("/users")
	public List<WorkModel> getAllUsers()
	{
		
		return this.springService1.getAll();
		
	}
	
	@GetMapping("/login")
	public String car()
	{
		return "Hello brother im spring login";
	}
	
	@GetMapping("/register")
	public String bike()
	{
		return "Hello brother im spring register";
	}
}
