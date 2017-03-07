package com.yetthin.web.serviceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yetthin.web.dao.JtdoaAPIDao;
import com.yetthin.web.dao.UrlRequestDao;
import com.yetthin.web.service.JtdoaAPIService;


@Service("JtdoaAPIService")
public class JtdoaAPIServiceImp implements JtdoaAPIService{
	@Resource
	private JtdoaAPIDao jtdoaAPIDao;
	@Resource 
	private UrlRequestDao urlRequestDao;
	 
	 
}
