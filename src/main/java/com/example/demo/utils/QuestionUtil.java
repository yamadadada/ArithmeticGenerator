package com.example.demo.utils;

import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuestionUtil {

    //按照String类型输出算术式
    public static String questionsToString(List<Char> charList) {
        List<Number> numberList = new ArrayList<>();
        List<Operator> operatorList = new ArrayList<>();
        List<Parentheses> parenthesesList = new ArrayList<>();
        for (Char c : charList) {
            if (c.getClass().equals(Number.class)) {
                numberList.add((Number) c);
            }
            else if (c.getClass().equals(Operator.class)) {
                operatorList.add((Operator) c);
            }
            else {
                parenthesesList.add((Parentheses) c);
            }
        }
        //对list按照id进行排序
        numberList.sort(Comparator.comparing(Number::getNId));
        StringBuilder result = new StringBuilder();
        for (Number number: numberList) {
            StringBuilder s = new StringBuilder(number.getOutput());
            //添加括号
            for (Parentheses p: parenthesesList) {
                //如果有前括号
                if (p.getStartNId() == number.getNId()) {
                    s = new StringBuilder("(").append(s);
                }else if (p.getEndNId() == number.getNId()) {//如果有后括号
                    s = s.append(")");
                }
            }
            if (number.getNId() <= operatorList.size()) {
                s.append(" ").append(findCharById(operatorList, number.getNId()).getOperator()).append(" ");
            }
            result = result.append(s);
        }
        return result.toString();
    }

    public static <T extends Char> T findCharById(List<T> charList, int id) {
        for (T c: charList) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new RuntimeException("list超出范围！，list=" + charList + ", id=" + id);
    }
}
