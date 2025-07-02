package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    // Method to retrieve all questions
    @GetMapping("/allQuestions")
    public ResponseEntity<?> allQues() {
        return questionService.getAllQuestions();
    }

    // Method to retrieve questions by category
    @GetMapping("/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        category = category.toLowerCase(); // Ensure category is in lowercase to match the database entries
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteById(id);
    }
    @PutMapping("/update/{id}")
    public Question updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }
}
