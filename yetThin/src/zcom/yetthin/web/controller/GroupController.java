package zcom.yetthin.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupreq;
import com.yetthin.web.service.GroupService;

@Controller
@RequestMapping(value="/group")
public class GroupController {
	
	@Resource
	private GroupService groupService;
	
	
	@ResponseBody
	@RequestMapping(value="/Detail",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String getDetail(@RequestParam(value="groupNameOrId")String groupNameOrId
			,HttpServletRequest http){
		
		String json =null;
		json =groupService.getDetail(groupNameOrId,http.getRequestURL().toString());
		return "{"+json+"}" ;
	}
	@ResponseBody
	@RequestMapping(value="/Component",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String getComponent(@RequestParam(value="groupNameOrId")String groupNameOrId){
		String json = null;
		json =groupService.getComponent(groupNameOrId);
		return "{"+json+"}" ;
	}
	@ResponseBody
	@RequestMapping(value="/Analyse",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	public String getAnalyse(@RequestParam(value="groupNameOrId")String groupNameOrId){
			String json=null;
			json =groupService.getAnalyse(groupNameOrId);
			return json ;
	}
	@ResponseBody
	@RequestMapping(value="/Recommend",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	public String getRecommend(@RequestParam(value="groupNameOrId")String groupNameOrId){
		String json =null;
		json =groupService.getRecommend(groupNameOrId);
		return json ;
	}
	@ResponseBody
	@RequestMapping(value="/saveRecommend",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	public String saveRecommend(@RequestParam(value="groupNameOrId")String groupNameOrId,
			@RequestParam(value="belongGroupId")String belongId,
			@RequestParam(value="upRecommendUserId")String upRecommendUserId,
			@RequestParam(value="repateContext")String context){
		String json =null;
		json =groupService.saveRecommend(groupNameOrId,belongId,upRecommendUserId,context);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/Summarize",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	public String getSummarize(@RequestParam(value="pageNum")int pageNum,
			@RequestParam(value="pageSize") int pageSize){
		String json =null;
		json =groupService.getSummarize(pageNum,pageSize);
		return json ;
	}
	@ResponseBody
	@RequestMapping(value="/stockType",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String getSotckType(){
		String json =null;
		json =groupService.getStockType();
		return json ;
	}
	@ResponseBody
	@RequestMapping(value="/stockTypeList",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String getSotckTypeList(@RequestParam(value="stockType")int stockType){
		String json =null;
		json =groupService.getStockTypeList(stockType);
		return json ;
	}
	@ResponseBody
	@RequestMapping(value="/stockofgroup",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String getStockofGroup(StockOfGroupreq req){
		String json =null;
		
		json =groupService.getStockofGroup(req);
		return json ;
	}
	@ResponseBody
	@RequestMapping(value="/stockofgroupSave",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String SotckofGroupSave(StockOfGroup stockOfGroup){
		String json =null;
		json =groupService.stockOfGroupSave(stockOfGroup);
		return json ;
	}
	
	
	
	
	 
}
