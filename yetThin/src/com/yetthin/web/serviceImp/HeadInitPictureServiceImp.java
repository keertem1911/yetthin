package com.yetthin.web.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yetthin.web.domain.HeadPicture;
import com.yetthin.web.persistence.HeadPictureMapper;
import com.yetthin.web.service.HeadInitPictureService;

import zcom.yetthin.web.controller.headInitPicture;
@Service("HeadInitPictureService")
public class HeadInitPictureServiceImp extends BaseService  implements HeadInitPictureService{
	
	@Resource
	private HeadPictureMapper headPictureMapper;
	@Override
	public HeadPicture get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(HeadPicture entity) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		HeadPicture pic =headPictureMapper.selectPicByid(entity.getId());
		if(pic==null) i=headPictureMapper.insert(entity);
		else i=headPictureMapper.saveOrUpdate(pic);
		return i;
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HeadPicture> getListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	 
	@Override
	public String getPictureList(String path) {
		
		List<HeadPicture> list1= headPictureMapper.getPictureList();
		
		 
		String json =tojson(list1);
		
		json =json.replace("/image/", getRequestPath(path)+"/image/");
		// TODO Auto-generated method stub
		return json;
	}

	@Override
	public int countByExample() {
		// TODO Auto-generated method stub
		return 0;
	}

	 

}
