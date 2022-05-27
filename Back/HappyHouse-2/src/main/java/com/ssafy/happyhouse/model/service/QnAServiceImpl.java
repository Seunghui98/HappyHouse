package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AnswerDto;
import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.mapper.QnAMapMapper;

@Service
public class QnAServiceImpl implements QnAService {
	
	@Autowired
	private QnAMapMapper qnaMapper;
	
	@Override
	public List<QuestionDto> selectList() throws SQLException {
		System.out.println("?");
		return qnaMapper.selectList();
	}

	@Override
	public boolean writeQuestion(QuestionDto question) throws SQLException {
		return qnaMapper.writeQuestion(question) == 1;
	}

	@Override
	public boolean writeAnswer(AnswerDto ans) throws SQLException {
		return qnaMapper.writeAnswer(ans) == 1;
	}

	@Override
	public QuestionDto selectQuestion(int no) throws SQLException {
		return qnaMapper.selectQuestion(no);
		
	}

	@Override
	public boolean deleteQuestion(int no) throws SQLException {
		return qnaMapper.deleteQuestion(no) == 1;
	}

	@Override
	public boolean deleteAnswer(int no) throws SQLException {
		return qnaMapper.deleteAnswer(no) == 1;
	}

	@Override
	public boolean modifyQuestion(QuestionDto question) throws SQLException {
		return qnaMapper.modifyQuestion(question) == 1;
	}

	@Override
	public boolean modifyAnswer(AnswerDto answer) throws SQLException {
		return qnaMapper.modifyAnswer(answer) == 1;
	}

	@Override
	public AnswerDto selectAnswer(int question_no) throws SQLException {
		// TODO Auto-generated method stub
		return qnaMapper.selectAnswer(question_no);
	}





}
