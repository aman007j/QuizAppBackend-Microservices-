package com.example.quizapp.controller;

import com.example.quizapp.model.Response;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    // localhost:8080/quiz/create?category=java&noOfQuestions=5&title=JQuiz
    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category,
                                     @RequestParam int noOfQuestions,
                                     @RequestParam String title) {
        return quizService.createQuiz(category, noOfQuestions, title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable Integer id) {
        // we can't send the whole quiz as it also contains right answers
        // so we will create a question wrapper class
        return quizService.getQuizQuestionsById(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.submitQuiz(id, responses);
    }

}
