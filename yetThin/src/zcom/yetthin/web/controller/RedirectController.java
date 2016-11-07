package zcom.yetthin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {
		
	@RequestMapping(value="/pageRequest/{name}",method=RequestMethod.GET)
	public String getPage(@PathVariable(value="name")String classname){
		return "/admin/"+classname;
	}
}
