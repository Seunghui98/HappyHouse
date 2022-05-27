package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;


public interface NoticeService {
	
	NoticeDto selectNotice(int no) throws SQLException;
	List<NoticeDto> selectList() throws SQLException;
	int writeNotice(NoticeDto notice) throws SQLException;
	int modifyNotice(NoticeDto notice) throws SQLException;
	int deleteNotice(int no) throws SQLException;
}
 