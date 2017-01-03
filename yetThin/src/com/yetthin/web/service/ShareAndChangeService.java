package com.yetthin.web.service;

import com.yetthin.web.domain.Share;

public interface ShareAndChangeService {

	String getShareInfoByGroupId(String groupId);

	Share getByShareId(String shareId);

	int deleteByShareId(String shareId);

	int updateAndSaveShare(String groupId, String stockList);

	int saveShare(Share share);

	String getShareChangeListAll(String groupId, String userId);
		 
}
