package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.CommentDto;

@Mapper
public interface CommentMapper {
	List<CommentDto> list(int Board_no);
	int create(CommentDto comment);
	int modify(CommentDto comment);
	int delete(int comment_no);
}
