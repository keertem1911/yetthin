package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.HeadPicture;


public interface HeadPictureMapper {
		int saveOrUpdate(HeadPicture pic);
		List<HeadPicture> getPictureList();
		HeadPicture selectPicByid(int id);
		int insert(HeadPicture pic);
}
