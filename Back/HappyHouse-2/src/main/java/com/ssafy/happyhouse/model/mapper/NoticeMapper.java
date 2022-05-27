package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> selectList();
	int writeNotice(NoticeDto notice);
	int deleteNotice(int no);
	NoticeDto selectNotice(int no);
	int modifyNotice(NoticeDto notice);
}
