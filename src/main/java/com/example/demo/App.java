package com.example.demo;

import com.example.demo.enums.CommandEnum;
import com.example.demo.pojo.Question;
import com.example.demo.service.QuestionService;

import java.util.regex.Pattern;

/**
 * 程序启动类
 */
public class App {

    //生成题目的个数
    private static int questionNumber = 10;

    //自然数、真分数和真分数分母允许的最大值
    private static int maxRange;

    private static QuestionService questionService = new QuestionService();

    public static void main(String[] args) {
        commandParser(args);
        for (int i = 1; i <= questionNumber; i++) {
            Question question = questionService.getQuestion(maxRange);
            question.setId(i);
        }
    }

    /**
     * 解析命令行参数
     * @param args
     */
    public static void commandParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(CommandEnum.N.getCommand()) || args[i].equals(CommandEnum.R.getCommand())) {
                //正则表达式验证是否为整数
                if (i + 1 < args.length && Pattern.matches("^[1-9]\\d*$", args[i + 1])) {
                    i = i + 1;
                    try {
                        if (args[i - 1].equals(CommandEnum.N.getCommand())) {
                            questionNumber = Integer.parseInt(args[i]);
                        } else {
                            maxRange = Integer.parseInt(args[i]);
                        }
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("参数不是正整数：" + args[i]);
                    }
                } else {
                    throw new RuntimeException("参数不是正整数或参数不能为空：" + args[i]);
                }
            } else {
                throw new RuntimeException("参数不正确：" + args[i]);
            }
        }
        //验证maxRange是否有输入
        if (maxRange == 0) {
            throw new RuntimeException("-r参数不能为空！");
        }
    }
}
