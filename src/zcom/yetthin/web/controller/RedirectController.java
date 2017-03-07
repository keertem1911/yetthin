package zcom.yetthin.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.service.PhoneVersionService;

@Controller
public class RedirectController {
		
	@Resource
	private PhoneVersionService phoneVersionService;
	@RequestMapping(value="/pageRequest/{name}",method=RequestMethod.GET)
	public String getPage(@PathVariable(value="name")String classname,
			HttpServletRequest request){

		if(classname.trim().equals("updateNewVersion")){
			HttpSession session = request.getSession();
			List<PhoneVersion> lists= phoneVersionService.getListAll();
			session.setAttribute("phones", lists);
		}
			
		return "/admin/"+classname;
	}
}
