package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.AnswerDto;
import com.ssafy.happyhouse.model.service.QnAService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/qna")
public class QnAController {

	@Autowired
	private QnAService qnaService;

	@GetMapping("/question")
	public ResponseEntity<?> getList() throws SQLException {
		List<QuestionDto> questions = qnaService.selectList();
		System.out.println(questions.size());
		if (questions != null) {
			for(int i=0;i<questions.size();i++) {
				AnswerDto answer = qnaService.selectAnswer(questions.get(i).getNo());
				if(answer == null) {
					questions.get(i).setAnswer(false);
				} else {
					questions.get(i).setAnswer(true);
				}
			}
			System.out.println(questions);
			return new ResponseEntity<List<QuestionDto>>(questions, HttpStatus.OK);
		} else
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/answer/{question_no}")
	public ResponseEntity<?> getAnswer(@PathVariable int question_no) throws SQLException {
		AnswerDto answerDto = qnaService.selectAnswer(question_no);
		if (answerDto != null) {
			return new ResponseEntity<AnswerDto>(answerDto,HttpStatus.OK);
		} else
			return new ResponseEntity<AnswerDto>(new AnswerDto(), HttpStatus.OK);
	}

	@PostMapping("/question")
	public ResponseEntity<?> writeQuestion(@RequestBody QuestionDto question) throws SQLException {
		if (qnaService.writeQuestion(question)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/answer")
	public ResponseEntity<?> writeAnswer(@RequestBody AnswerDto answer) throws SQLException {
		if (qnaService.writeAnswer(answer)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/question/{no}")
	public ResponseEntity<?> selectQuestion(@PathVariable int no) throws SQLException{
		QuestionDto quest = qnaService.selectQuestion(no);
		if(quest != null) {
			return new ResponseEntity<QuestionDto>(quest,HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/question/{no}")
	public ResponseEntity<?> modifyQuestion(@RequestBody QuestionDto question, @PathVariable int no) throws SQLException{
		if(qnaService.modifyQuestion(question)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/answer")
	public ResponseEntity<?> modifyAnswer(@RequestBody AnswerDto answer) throws SQLException{
		if(qnaService.modifyAnswer(answer)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/question/{no}")
	public ResponseEntity<?> deleteQuestion(@PathVariable int no) throws SQLException{
		if(qnaService.deleteQuestion(no)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/answer/{no}")
	public ResponseEntity<?> deleteAnswer(@PathVariable int no) throws SQLException{
		if(qnaService.deleteAnswer(no)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else
			return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
}
