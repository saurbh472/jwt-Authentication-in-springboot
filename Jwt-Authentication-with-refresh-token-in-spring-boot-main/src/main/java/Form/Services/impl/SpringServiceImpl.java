package Form.Services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Form.Model.WorkModel;
import Form.Services.SpringService;

@Service
public class SpringServiceImpl implements SpringService{


	List<WorkModel> list = new ArrayList<>();
	
	public SpringServiceImpl()
	{
		list.add(new WorkModel("saurbh","saurbh","saurbh@gmail.com"));
		list.add(new WorkModel("pankaj","pankaj","pankaj@gmail.com"));
		list.add(new WorkModel("anurag","anurag","anurag@gmail.com"));
		list.add(new WorkModel("shubham","shubham","shubham@gmail.com"));
		list.add(new WorkModel("sorav","sorav","sorav@gmail.com"));
	}

	@Override
	public List<WorkModel> getAllUsers() {
		
		return this.list;
	}
}
