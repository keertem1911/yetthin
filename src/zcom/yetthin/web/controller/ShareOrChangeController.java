package zcom.yetthin.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yetthin.web.service.ShareAndChangeService;
@Controller
@RequestMapping("/share")
public class ShareOrChangeController extends BaseController{
	
		@Resource(name ="ShareAndChangeService")
		ShareAndChangeService shareAndChangeService;
		private String josnToSaveUpdateDelete(int status){
			StringBuffer buffer= new StringBuffer();
			buffer.append("{");
			buffer.append("\"status\":");
			if(status!=0)
				buffer.append("\"200\"");
			else
				buffer.append("\"500\"");
			buffer.append("}");
			return buffer.toString();
		}
		
		/**
		 * 获取组合的仓位信息
		 * @param groupId
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/getShareInfoByGroupId",method= RequestMethod.POST,
		produces={"application/json;charset=utf-8"})
		public String getShareInfoByGroupId(String groupId){
			String json =null;
			json = shareAndChangeService.getShareInfoByGroupId(groupId);
			return json ;
		}
 
		/**
		 * 保存 调整仓位信息表
		 * @param 
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/changeShare",method= RequestMethod.POST,
		produces={"application/json;charset=utf-8"})
		public String updateAndSaveShare(@RequestParam(value="groupId")String groupId,
				@RequestParam(value="stocks")String stockList){
			int i = shareAndChangeService.updateAndSaveShare(groupId,stockList);
			return josnToSaveUpdateDelete(i);
		}
		@ResponseBody
		@RequestMapping(value="/showShareChangeDate",method=RequestMethod.POST,
		produces={"application/json;charset=utf-8"})
		public String getShareChangeListAll(@RequestParam(value="groupId")String groupId,
				@RequestParam(value="userId")String userId){
			String json =null;
			json = shareAndChangeService.getShareChangeListAll(groupId,userId);
			return json;
		}

		@Override
		public Class getClassType() {
			// TODO Auto-generated method stub
			return this.getClass();
		}
		
}
