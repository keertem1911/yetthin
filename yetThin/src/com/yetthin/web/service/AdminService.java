package com.yetthin.web.service;


import com.yetthin.web.domain.Admin;

public interface AdminService extends BaseService<Admin>{
		public abstract int login(Admin admin);
		public abstract Admin getByName(String name);
		public abstract String changePwd(Integer id, String newPwd);
		public abstract int changeName(String string, String userName);
}
