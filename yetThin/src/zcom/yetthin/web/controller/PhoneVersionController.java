package zcom.yetthin.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.service.PhoneVersionService;

@Controller
@RequestMapping("/help")
public class PhoneVersionController {
	@Resource(name="PhoneVersionService")
	private PhoneVersionService phoneVersionService;
	
	/**
	 * 版本更新Android
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkNewVersion",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> checkNewVersion(){
		Map<String, Object> map=new HashMap<>();
		String msg=null;
		String statusCode="200";
		System.out.println("come into checkNewVersion $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		PhoneVersion pv=new PhoneVersion();
		pv.setApkUrl("www.baidu.com");
		pv.setExplain("版本1 wwwhawavfeawsiofhawihfoi阿瓦打我的");
		pv.setVersionCode("#include <stdio.h>");
		pv.setVersionName("yetthin1.1");
		map.put("items", pv);
		if(msg!=null&&!"".equals(msg))
		map.put("msg", msg);
		map.put("status", statusCode);
		return map;
	}
}
