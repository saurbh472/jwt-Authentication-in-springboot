package Form.Services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Form.Model.WorkModel;
import Form.Services.SpringService1;

@Service
public class SpringServiceImpl1 implements SpringService1{

	List<WorkModel> list = new ArrayList<>();
	
	public SpringServiceImpl1()
	{
		list.add(new WorkModel("jagrati","Mishra","jagrati@gmail.com"));
		list.add(new WorkModel("sky","pankaj","sky@gmail.com"));
		list.add(new WorkModel("universe","stars","universe@gmail.com"));
		list.add(new WorkModel("niral","controller","shubham@gmail.com"));
		list.add(new WorkModel("edge","client","sorav@gmail.com"));
	}

	@Override
	public List<WorkModel> getAll() {
		
		return this.list;
	}
}
