package com.yetthin.web.service;

import java.util.List;

public interface BaseService <IdEntity>{
	
	public abstract IdEntity get(String id);

	public abstract int save(IdEntity entity) throws Exception;

	public abstract int delete(String id) throws Exception;

	public abstract List<IdEntity> getListAll();

	public abstract int countByExample();
}
