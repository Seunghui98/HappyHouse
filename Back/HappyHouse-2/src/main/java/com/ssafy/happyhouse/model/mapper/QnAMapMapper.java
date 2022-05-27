package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.QuestionDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.AnswerDto;

@Mapper
public interface QnAMapMapper {
	
	List<QuestionDto> selectList();
	int writeQuestion(QuestionDto question);
	int writeAnswer(AnswerDto ans);
	QuestionDto selectQuestion(int no);
	int deleteQuestion(int no);
	int deleteAnswer(int no);
	int modifyQuestion(QuestionDto question);
	int modifyAnswer(AnswerDto ans);
	AnswerDto selectAnswer(int question_no);
	
}
