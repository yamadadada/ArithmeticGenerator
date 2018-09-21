package com.example.demo.service;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;
import com.example.demo.utils.MathUtil;
import com.example.demo.utils.QuestionUtil;

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
        List<Operator> operatorList = new LinkedList<>();
        List<Char> charList = new LinkedList<>();
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
        List<Parentheses> parenthesesList = new LinkedList<>();
        //运算符大于1才生成括号
        if (operatorList.size() > 1) {
            Parentheses p = null;
            for (int i = 1; i <= operatorList.size(); i++) {
                if (p != null && i == operatorList.size()) {
                    if (p.getStartNId() == 1) {
                        break;
                    }
                    p.setEndNId(i + 1);
                    parenthesesList.add(p);
                    break;
                }
                boolean flag = r.nextBoolean();
                if (flag && p != null) {
                    p.setEndNId(i + 1);
                    parenthesesList.add(p);
                    p = null;
                    i = i + 1;
                }
                if (!flag) {
                    p = new Parentheses();
                    p.setStartNId(i);
                    if (r.nextBoolean()) {
                        p.setEndNId(i + 1);
                        parenthesesList.add(p);
                        p = null;
                        i = i + 1;
                    }
                }
            }
        }
        //构造运算符优先处理链
        List<Operator> buildList = QuestionUtil.buildLink(operatorList, parenthesesList);
        //计算结果
        List<Char> tempList = new LinkedList<>();
        tempList.addAll(charList);
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
            return new Number(nid, NumberTypeEnum.NATURAL_NUMBER.getCode(), r.nextInt(maxRange - 1) + 1);
        } else {
            CalculateFraction cf = MathUtil.reduction(r.nextInt(maxRange - 1) + 1, r.nextInt(maxRange - 1) + 1);
            Number number = cf.getNumber();
            number.setNId(nid);
            return number;
        }
    }
}
