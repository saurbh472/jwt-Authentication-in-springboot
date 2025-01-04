package Form.service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Form.Model.NormalModel;
import Form.service.MainService;

@Service
public class MainServiceImpl implements MainService {

  List<NormalModel> list = new ArrayList<>();
	
	public MainServiceImpl()
	{
		list.add(new NormalModel("saurbh","saurbh","sds@gmail.com"));
		list.add(new NormalModel("pankaj","pankaj","xyz@gmail.com"));
		list.add(new NormalModel("anurag","anurag","xyz@gmail.com"));
		list.add(new NormalModel("shubham","shubham","sds@gmail.com"));
		list.add(new NormalModel("sorav","sorav","sdsd@gmail.com"));
	}

	@Override
	public List<NormalModel> getall() {
		
		return this.list;
	}

}
	
	


