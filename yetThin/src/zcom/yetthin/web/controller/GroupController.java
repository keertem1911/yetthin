package zcom.yetthin.web.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yetthin.web.domain.Message;
import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupreq;
import com.yetthin.web.domain.User;
import com.yetthin.web.service.GroupService;
import com.yetthin.web.service.UserInfoService;


@Controller
@RequestMapping(value="/group")
public class GroupController extends BaseController{
	
	@Resource
	private GroupService groupService;
	@Resource
	private UserInfoService userService;
	
	@ResponseBody
	@RequestMapping(value="/Detail",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String getDetail(@RequestParam(value="groupNameOrId")String groupNameOrId,
			@RequestParam(value="userId",required=false,defaultValue="")String userId,
			HttpServletRequest http){
		
		String json =null;
		logger.info(groupNameOrId+" come into -------------------------------");
		json =groupService.getDetail(groupNameOrId,http.getRequestURL().toString(),userId);
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
	public String getRecommend(@RequestParam(value="groupNameOrId")String groupNameOrId,
			HttpServletRequest req){
		String json =null;
		StringBuffer path =req.getRequestURL();
		json =groupService.getRecommend(groupNameOrId,path.toString().split("/group")[0]);
		return json ;
	}
	/**
	 * 
	 *  
	 * @param belongGroupId  若为评论则值取  组合Id 回复则取 评论Id
	 * @param upRecommendUserId 上级用户Id ，评论为0 回复有值
	 * @param repateContext 评论/回复内容
	 * @param userid 发起人Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveRecommend",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	public String saveRecommend(
			@RequestParam(value="belongGroupId")String belongGroupId,
			@RequestParam(value="upRecommendUserId")String upRecommendUserId,
			@RequestParam(value="repateContext")String repateContext,
			@RequestParam(value="userId")String userid){
		String json =null;
		json =groupService.saveRecommend(
				belongGroupId,
				upRecommendUserId,
				repateContext,
				userid);
		return json;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/Summarize",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	public String getSummarize(@RequestParam(value="pageNum")int pageNum,
			@RequestParam(value="pageSize") int pageSize){
		String json =null;
		logger.info("Summarize ............................");
		json =groupService.getSummarize(pageNum,pageSize);
		return json ;
	}
	
	@ResponseBody
	@RequestMapping(value="/SummarizeMyCollection",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String getSummarizeMyCollection(@RequestParam(value="userId")String userId,
			@RequestParam(value="pageNum")int pageNum,
			@RequestParam(value="pageSize") int pageSize){
		String json =null;
		logger.info("Summarize ............................");
		json =groupService.getSummarizeFromMyCollection(userId, pageNum, pageSize);
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
		System.out.println(req);
		json =groupService.getStockofGroup(req);
		return json ;
	}
	
	@ResponseBody
	@RequestMapping(value="/stocksofgroupSave",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String SotckofGroupSave(StockOfGroup stockOfGroup){
		String json =null;
		
		json =groupService.stockOfGroupSave(stockOfGroup);
		return json ;
	}
	@ResponseBody
	@RequestMapping(value="/deleteMyGroups",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String deleteMyGroups(@RequestParam(value="userID")String userId,
			@RequestParam(value="groupId")String groupId,
			HttpServletRequest request){
		String statusCode ="200";
		String message= "";
		HttpSession session = request.getSession();
		User user = userService.get(userId);
		if(user.getUserId().equals(userId.trim())){
			int i =groupService.deleteMyGroups(userId,groupId);
			if(i==0){
				statusCode="522";
				message="用户所属组合不存在";
				
			}
		}else{
			statusCode="522";
			message="用户非法";
		}
		 
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+message+"\"}";
	}
	@Override
	public Class getClassType() {
		// TODO Auto-generated method stub
		return this.getClass();
	}
	/**
	 * 设置点赞 
	 */
	@ResponseBody
	@RequestMapping(value="/myAgree",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String updateMyAgree(@RequestParam(value="userId")String userId,
			@RequestParam(value="groupId")String groupId
			){
			User user = userService.get(userId);
			String json =null;
			if(user!=null){
				json =groupService.saveMyAgree(groupId, userId);
				
			}else{
				Message msg =new Message();
				msg.setInfo("用户不存在");
				msg.setStatus("500");
				json = JSON.toJSONString(msg);
			}
			return json;
	}
	/**
	 * 取消点赞
	 */
	@ResponseBody
	@RequestMapping(value="/cancelMyAgree",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String cancelMyAgree(@RequestParam(value="userId")String userId,
			@RequestParam(value="groupId")String groupId
			){
			User user = userService.get(userId);
			String json =null;
			if(user!=null){
				json =groupService.deleteMyAgreeByGroupIdAndUserId(groupId, userId);
				
			}else{
				Message msg =new Message();
				msg.setInfo("用户不存在");
				msg.setStatus("500");
				json = JSON.toJSONString(msg);
			}
			return json;
	}
	/**
	 * 添加收藏
	 */
	@ResponseBody
	@RequestMapping(value="/myCollection",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String updateMyCollection(@RequestParam(value="userId")String userId,
			@RequestParam(value="groupId")String groupId
			){
		User user = userService.get(userId);
		String json =null;
		if(user!=null){
			json =groupService.saveMyCollection(groupId, userId);
			
		}else{
			Message msg =new Message();
			msg.setInfo("用户不存在");
			msg.setStatus("500");
			json = JSON.toJSONString(msg);
		}
		return json;
	}
	/**
	 * 取消收藏
	 */
	@ResponseBody
	@RequestMapping(value="/cancelMyCollection",method=RequestMethod.POST,
	produces={"application/json;charset=utf-8"})
	public String cancelMyCollection(@RequestParam(value="userId")String userId,
			@RequestParam(value="groupId")String groupId
			){
		User user = userService.get(userId);
		String json =null;
		if(user!=null){
			json =groupService.cancelMyCollection(groupId, userId);
			
		}else{
			Message msg =new Message();
			msg.setInfo("用户不存在");
			msg.setStatus("500");
			json = JSON.toJSONString(msg);
		}
		return json;
	}
	
}
