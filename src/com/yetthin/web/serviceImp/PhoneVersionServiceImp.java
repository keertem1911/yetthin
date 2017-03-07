package com.yetthin.web.serviceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.persistence.PhoneVersionMapper;
import com.yetthin.web.service.PhoneVersionService;

@Service("PhoneVersionService")
public class PhoneVersionServiceImp implements PhoneVersionService{
	
	@Resource
	private PhoneVersionMapper phoneVersionMapper;
	@Override
	public PhoneVersion get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public int save(PhoneVersion entity) throws Exception {
		// TODO Auto-generated method stub
		return phoneVersionMapper.insertSelective(entity);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	 

	 
	@Override
	public List<PhoneVersion> getListAll() {
		// TODO Auto-generated method stub
		return phoneVersionMapper.getAll();
	}

	@Override
	public int countByExample() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public int update(PhoneVersion p) {
		// TODO Auto-generated method stub
		int i =phoneVersionMapper.updateByPrimaryKeySelective(p);
		return i;
	}

}
