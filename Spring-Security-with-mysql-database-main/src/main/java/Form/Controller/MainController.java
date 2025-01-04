package Form.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Form.Model.NormalModel;
import Form.service.MainService;


@RestController
@RequestMapping("/normal")
public class MainController {
	
	
	
	@Autowired
	private MainService mainService;
	
	
	
	@GetMapping("/users")
	public List<NormalModel> getallData(){
		return mainService.getall();
		
	}
}
