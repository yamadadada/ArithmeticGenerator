package com.example.demo.service;

import com.example.demo.pojo.Parentheses;
import com.example.demo.pojo.Question;
import com.example.demo.utils.QuestionUtil;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void getParenthesesList() {
        for (int i = 0; i < 10000; i++) {
            List<Parentheses> parenthesesList = new LinkedList<>();
            questionService.getParenthesesList(1, 4, parenthesesList);
            for (Parentheses p : parenthesesList) {
                if (p.getStartNId() == p.getEndNId() || p.getEndNId() - p.getStartNId() == 3) {
                    throw new RuntimeException();
                }
                System.out.println(p);
            }
        }
    }
}