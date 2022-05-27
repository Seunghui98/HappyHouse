package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentmapper;
	
	@Override
	public List<CommentDto> list(int Board_no) {
		return commentmapper.list(Board_no);
	}

	@Override
	public int create(CommentDto commentDto) {
		return commentmapper.create(commentDto);
	}

	@Override
	public int modify(CommentDto commentDto) {
		return commentmapper.modify(commentDto);
	}

	@Override
	public int delete(int comment_no) {
		return commentmapper.delete(comment_no);
	}

}
