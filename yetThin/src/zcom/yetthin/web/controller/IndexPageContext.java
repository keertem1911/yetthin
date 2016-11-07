package zcom.yetthin.web.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yetthin.web.service.IndexPageContextService;

@Controller
public class IndexPageContext {
	
	@Resource
	private IndexPageContextService IndexPageContextService;
	@ResponseBody
	@RequestMapping(value="/incomeRecommendList",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String incomeRecommendList(
			@RequestParam(value="IncomeType")int type,
			@RequestParam(value="pageNum")int pageNum,
			@RequestParam(value="pageSize")int pageSize){
		String json =null;
		json =IndexPageContextService.getIncomeRecommendList(type, pageNum, pageSize);
		
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/bestIncomeList",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String bestIncomeList(
			@RequestParam(value="pageNum")int pageNum,
			@RequestParam(value="pageSize")int pageSize,
			HttpServletRequest req){
		String json =null;
		json =IndexPageContextService.getBestIncomeList(pageNum,pageSize,req.getRequestURL().toString());
		return json;
	}
	@RequestMapping(value="/currentIncomeList",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String currentIncomeList(@RequestParam(value="groupNameOrId")String groupNameOrId,
			@RequestParam(value="pageNum")int pageNum,
			@RequestParam(value="pageSize")int pageSize,
			@RequestParam(value="type")int type){
		String json =null;
		json =IndexPageContextService.getCurrentIncomeList(groupNameOrId, pageNum, pageSize, type);
		return "{ \"value\":"+json+"}";
	}
	@ResponseBody
	@RequestMapping(value="/userGroups",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	public String getUserGroups(@RequestParam(value="userName")String userName,
			@RequestParam(value="pageNum")int pageNum,@RequestParam(value="pageSize")int pageSize){
		String json=null ;
		json =IndexPageContextService.getUserGroups(userName,pageNum,pageSize);
		return  json ;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadInterface",method=RequestMethod.POST)
	public String uploadRequestInterface(@RequestParam(value="requestIntface")MultipartFile requestIntface,
			HttpServletRequest request){
		String json=null;
		String path=request.getSession().getServletContext().getRealPath("");
		path=path+"/excl/interfacerequest.xls";
		File file =new File(path);
//		file.deleteOnExit();
//		System.out.println("delete exit");
		try {
			requestIntface.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "500";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "500";
		}
		System.out.println(path);
		return "200";
	}
}
