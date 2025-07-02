package com.example.quizapp.service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.dao.QuizDao;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<?> createQuiz(String category, int noOfQuestions, String title) {
        List<Question> ques = questionDao.findRandomQuestionByCategory(category, noOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(ques);
        quiz = quizDao.save(quiz);

        return ResponseEntity.ok(quiz);
    }

    public ResponseEntity<?> getQuizQuestionsById(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);

        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
                    q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<?> submitQuiz(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);

        List<Question> questions = quiz.get().getQuestions();
        int score = 0;
        int i = 0;
        for(Response response : responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
                score++;
            }
            i++;
        }
        return new ResponseEntity<>("Your score is: " + score + "/" + questions.size(), HttpStatus.OK);
    }
}
