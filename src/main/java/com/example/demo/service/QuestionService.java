package com.example.demo.service;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;
import com.example.demo.utils.MathUtil;
import com.example.demo.utils.QuestionUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionService {

    /**
     * 生成指定范围的一道题目
     * @param maxRange
     * @return 返回一个带运算式和结果的Question
     */
    public Question getQuestion(int maxRange) {
        Question question = new Question();
        Random r = new Random();
        int operatorNum = r.nextInt(3) + 1;
        List<Operator> operatorList = new ArrayList<>();
        List<Char> charList = new ArrayList<>();
        for (int i = 0; i < operatorNum; i++) {
            //生成运算符
            OperatorEnum operatorEnum = OperatorEnum.values()[r.nextInt(4)];
            Operator operator = new Operator(i + 1, operatorEnum.getOperator());
            operatorList.add(operator);
            //生成数字
            Number number = createNumber(maxRange, i + 1);
            charList.add(number);
            charList.add(operator);
        }
        //生成最后一个数字并加入链表
        charList.add(createNumber(maxRange, operatorNum + 1));
        //生成括号
        List<Parentheses> parenthesesList = new ArrayList<>();
        getParenthesesList(1, operatorList.size() + 1, parenthesesList);
        //构造运算符优先处理链
        List<Operator> buildList = QuestionUtil.buildLink(operatorList, parenthesesList);
        question.setBuildList(buildList);
        //计算结果
        List<Char> tempList = new LinkedList<>(charList);
        for(int i = 0; i < buildList.size(); i++) {
            Operator o = buildList.get(i);
            int temp = tempList.indexOf(o);
            Number n1 = (Number)tempList.get(temp - 1);
            Number n2 = (Number)tempList.get(temp + 1);
            Number n = MathUtil.calculate(n1, o, n2);
            //返回null表示为减法得出是负数或被除数为0，题目无效返回
            if (n == null) {
                return null;
            }
            for (int j = 0; j < 3; j++) {
                tempList.remove(temp - 1);
            }
            tempList.add(temp - 1, n);
            if (tempList.size() == 1) {
                question.setResult(n.getOutput());
            }
        }
        question.setCharList(charList);
        question.setParenthesesList(parenthesesList);
        return question;
    }

    private Number createNumber(int maxRange, int nid) {
        Random r = new Random();
        NumberTypeEnum numberTypeEnum = NumberTypeEnum.values()[r.nextInt(2)];
        if (numberTypeEnum.equals(NumberTypeEnum.NATURAL_NUMBER)) {
            return new Number(nid, NumberTypeEnum.NATURAL_NUMBER.getCode(), r.nextInt(maxRange));
        } else {
            CalculateFraction cf = MathUtil.reduction(r.nextInt(maxRange - 1) + 1, r.nextInt(maxRange - 1) + 1);
            Number number = cf.getNumber();
            number.setNId(nid);
            return number;
        }
    }

    public void getParenthesesList(int startNid, int endNid, List<Parentheses> parenthesesList) {
        if (endNid - startNid > 1) {
            Random r = new Random();
            Parentheses p = null;
            for (int i = startNid; i <= endNid - 1; i++) {
                if (p != null && i == endNid - 1) {
                    if (p.getStartNId() == startNid) {
                        break;
                    }
                    p.setEndNId(i + 1);
                    parenthesesList.add(p);
                    getParenthesesList(p.getStartNId(), p.getEndNId(), parenthesesList);
                    break;
                }
                boolean flag = r.nextBoolean();
                if (p != null || !flag) {
                    if (!flag) {
                        p = new Parentheses();
                        p.setStartNId(i);
                    }
                    if (flag || r.nextBoolean()) {
                        p.setEndNId(i + 1);
                        parenthesesList.add(p);
                        getParenthesesList(p.getStartNId(), p.getEndNId(), parenthesesList);
                        p = null;
                        i = i + 1;
                    }
                }
            }
        }
    }
}
