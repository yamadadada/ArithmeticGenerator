package com.example.demo.service;

import com.example.demo.pojo.Question;

import java.util.List;

public class RepeatService {

    /**
     * 检查题目是否与已有题目重复
     * @param questionList
     * @param question
     * @return
     */
    public boolean isRepeat(List<Question> questionList, Question question) {
        for (Question origin: questionList) {
            //先检查题目答案是否相同再进行下一步
            if (origin.getResult().equals(question.getResult())) {

            }
        }
        return false;
    }
}
