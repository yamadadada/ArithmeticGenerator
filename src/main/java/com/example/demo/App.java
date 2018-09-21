package com.example.demo;

import com.example.demo.dao.OutputDao;
import com.example.demo.enums.CommandEnum;
import com.example.demo.pojo.Question;
import com.example.demo.service.QuestionService;
import com.example.demo.service.RepeatService;
import com.example.demo.utils.QuestionUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 程序启动类
 */
public class App {

    private static final int CREATE = 0;

    private static final int CHECK = 1;

    //生成题目的个数
    private static int questionNumber = 10;

    //自然数、真分数和真分数分母允许的最大值
    private static int maxRange;

    public static void main(String[] args) {
        int flag = commandParser(args);
        if (flag == CREATE) {
            QuestionService questionService = new QuestionService();
            RepeatService repeatService = new RepeatService();
            List<Question> questionList = new LinkedList<>();
            for (int i = 1; i <= questionNumber; i++) {
                Question question = questionService.getQuestion(maxRange);
                //检查重复题目
                while (question == null || repeatService.isRepeat(questionList, question)) {
                    question = questionService.getQuestion(maxRange);
                }
                question.setId(i);
                questionList.add(question);
            }
            for (Question q: questionList) {
                System.out.println("[" + q.getId() + "]" + QuestionUtil.charListToString(q.getCharList(), q.getParenthesesList()));
            }
            OutputDao outputDao = new OutputDao();
            outputDao.toExercises(questionList);
            System.out.println("输出到文件成功！");
        }
    }

    /**
     * 解析命令行参数
     * @param args
     */
    public static int commandParser(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("请输入参数！");
        }
        if (args[0].equals(CommandEnum.E.getCommand())) {
            if (args.length == 4 && args[2].equals(CommandEnum.A.getCommand())) {
                if (Pattern.matches(".*\\.txt", args[1]) && Pattern.matches(".*\\.txt", args[3])) {
                    OutputDao outputDao = new OutputDao();
                    outputDao.correct(args[1], args[3]);
                    return CHECK;
                } else {
                    throw new RuntimeException("参数不正确：" + args[1] + ", " + args[3]);
                }
            } else {
                throw new RuntimeException("参数格式不正确！");
            }
        }
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
        return CREATE;
    }
}
