package com.example.demo.service;

import com.example.demo.pojo.Question;
import com.example.demo.utils.QuestionUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionServiceTest {

    QuestionService questionService = new QuestionService();

    @Test
    public void getQuestion() {
        Question question = questionService.getQuestion(10);
        if (question == null) {
            System.out.println("其中有负数！");
        }
        System.out.println(QuestionUtil.charListToString(question.getCharList(), question.getParenthesesList()));
        System.out.println(question.getResult());
    }
}