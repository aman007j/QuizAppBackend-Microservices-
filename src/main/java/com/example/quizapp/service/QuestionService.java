package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<?> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK); // use ResponseEntity to send both data and status code
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        if (question.getQuestionTitle() == null || question.getCategory() == null) {
            throw new IllegalArgumentException("Question text and category must not be null");
        }
        return questionDao.save(question);
    }

    public void deleteById(Integer id) {
        if (!questionDao.existsById(id)) {
            throw new IllegalArgumentException("Question with id " + id + " does not exist");
        }
        questionDao.deleteById(id);
    }

    public Question updateQuestion(Integer id, Question question) {
        Question ques = questionDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question with id " + id + " does not exist"));

        if (question.getQuestionTitle() != null) {
            ques.setQuestionTitle(question.getQuestionTitle());
        }
        if (question.getOption1() != null) {
            ques.setOption1(question.getOption1());
        }
        if (question.getOption2() != null) {
            ques.setOption2(question.getOption2());
        }
        if (question.getOption3() != null) {
            ques.setOption3(question.getOption3());
        }
        if (question.getOption4() != null) {
            ques.setOption4(question.getOption4());
        }
        if (question.getRightAnswer() != null) {
            ques.setRightAnswer(question.getRightAnswer());
        }
        if (question.getDifficultyLevel() != null) {
            ques.setDifficultyLevel(question.getDifficultyLevel());
        }
        if (question.getCategory() != null) {
            ques.setCategory(question.getCategory());
        }
        return questionDao.save(ques);
    }
}