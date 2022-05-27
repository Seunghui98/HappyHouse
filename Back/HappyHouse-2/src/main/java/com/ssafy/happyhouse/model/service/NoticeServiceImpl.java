package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticemapper;
	@Override
	public NoticeDto selectNotice(int no) throws SQLException {
		return noticemapper.selectNotice(no);
	}

	@Override
	public List<NoticeDto> selectList() throws SQLException {
		return noticemapper.selectList();
	}

	@Override
	public int writeNotice(NoticeDto notice) throws SQLException {
		return noticemapper.writeNotice(notice);
	}

	@Override
	public int modifyNotice(NoticeDto notice) throws SQLException {
		return noticemapper.modifyNotice(notice);
	}

	@Override
	public int deleteNotice(int no) throws SQLException {
		return noticemapper.deleteNotice(no);
	}

	

}
