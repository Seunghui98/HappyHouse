package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.AnswerDto;

public interface QnAService {
	
	AnswerDto selectAnswer(int question_no) throws SQLException;
	List<QuestionDto> selectList() throws SQLException;
	boolean writeQuestion(QuestionDto question) throws SQLException;
	boolean writeAnswer(AnswerDto ans) throws SQLException;
	QuestionDto selectQuestion(int no) throws SQLException;
	boolean deleteQuestion(int no) throws SQLException;
	boolean deleteAnswer(int no) throws SQLException;
	boolean modifyQuestion(QuestionDto question) throws SQLException;
	boolean modifyAnswer(AnswerDto answer) throws SQLException;
}
