package com.yetthin.web.serviceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yetthin.web.domain.Admin;
import com.yetthin.web.persistence.AdminMapper;
import com.yetthin.web.service.AdminService;

@Service("AdminService")
public class AdminServiceImp implements AdminService{
	
	@Resource
	AdminMapper adminMapper;
	 
	
	@Override
	public Admin get(String id) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public int save(Admin entity) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.insertSelective(entity);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public List<Admin> getListAll() {
		// TODO Auto-generated method stub
		return adminMapper.getAll();
	}

	@Override
	public int countByExample() {
		// TODO Auto-generated method stub
		return adminMapper.countById();
	}
	
	@Override
	public int login(Admin admin) {
		Admin adminNew =this.getByName(admin.getAdminName());
		int i=0;
		if(adminNew==null){
			i=-1;
		}else if(!adminNew.getAdminPassword().equals(admin.getAdminPassword())){
			i=-2;
		}else{
			i=adminNew.getId();
		}
		
		return i;
	}

	@Override
	public Admin getByName(String name) {
		// TODO Auto-generated method stub
		return adminMapper.selectByName(name);
	}

	@Override
	public String changePwd(Integer id, String newPwd) {
		// TODO Auto-generated method stub
		Admin admin =adminMapper.selectByPrimaryKey(id);
		admin.setAdminPassword(newPwd);
		int i=adminMapper.updateByPrimaryKey(admin);
		return i>0?"200":"error";
	}

	@Override
	public int changeName(String string, String userName) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(string);
		Admin admin=adminMapper.selectByPrimaryKey(id);
		admin.setAdminName(userName);
		
		return adminMapper.updateByPrimaryKey(admin);
	}

}
