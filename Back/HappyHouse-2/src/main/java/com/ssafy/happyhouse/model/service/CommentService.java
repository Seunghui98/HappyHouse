package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.CommentDto;

public interface CommentService {
	List<CommentDto> list(int Board_no);

	int create(CommentDto commentDto);

	int modify(CommentDto commentDto);

	int delete(int comment_no);
}
