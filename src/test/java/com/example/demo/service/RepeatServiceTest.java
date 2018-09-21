package com.example.demo.service;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;
import com.example.demo.utils.QuestionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RepeatServiceTest {

    RepeatService repeatService = new RepeatService();

    @Test
    public void isRepeat() {
        List<Question> questionList = new LinkedList<>();
        Question q1 = new Question();
        List<Char> charList1 = new LinkedList<>();
        Number n1 = new Number(1, NumberTypeEnum.NATURAL_NUMBER.getCode(), 3);
        Number n2 = new Number(2, NumberTypeEnum.NATURAL_NUMBER.getCode(), 2);
        Number n3 = new Number(3, NumberTypeEnum.NATURAL_NUMBER.getCode(), 1);
        Operator o1 = new Operator(1, OperatorEnum.PLUS.getOperator());
        Operator o2 = new Operator(2, OperatorEnum.PLUS.getOperator());
        Parentheses parentheses = new Parentheses(2, 3);
        List<Parentheses> parenthesesList = new LinkedList<>();
        parenthesesList.add(parentheses);
        charList1.add(n1);
        charList1.add(o1);
        charList1.add(n2);
        charList1.add(o2);
        charList1.add(n3);
        q1.setCharList(charList1);
        q1.setParenthesesList(parenthesesList);
        questionList.add(q1);
        Question q2 = new Question();
        List<Char> charList2 = new LinkedList<>();
        Number n4 = new Number(1, NumberTypeEnum.NATURAL_NUMBER.getCode(), 1);
        Number n5 = new Number(2, NumberTypeEnum.NATURAL_NUMBER.getCode(), 2);
        Number n6 = new Number(3, NumberTypeEnum.NATURAL_NUMBER.getCode(), 3);
        Operator o3 = new Operator(1, OperatorEnum.PLUS.getOperator());
        Operator o4 = new Operator(2, OperatorEnum.PLUS.getOperator());
        charList2.add(n4);
        charList2.add(o3);
        charList2.add(n5);
        charList2.add(o4);
        charList2.add(n6);
        q2.setCharList(charList2);
        q1.setResult("68");
        q2.setResult("68");
        Assert.assertEquals(true, repeatService.isRepeat(questionList, q2));
    }

    @Test
    public void buildLink() {
        List<Operator> operatorList = new LinkedList<>();
        Operator o1 = new Operator(1, OperatorEnum.PLUS.getOperator());
        Operator o2 = new Operator(2, OperatorEnum.SUBTRACTION.getOperator());
        Operator o3 = new Operator(3, OperatorEnum.MULTIPLICATION.getOperator());
        operatorList.add(o1);
        operatorList.add(o2);
        operatorList.add(o3);
        List<Parentheses> parenthesesList = new LinkedList<>();
        Parentheses p1 = new Parentheses(3, 4);
        Parentheses p2 = new Parentheses(1, 2);
        parenthesesList.add(p1);
        parenthesesList.add(p2);
        List<Operator> result = QuestionUtil.buildLink(operatorList, parenthesesList);
        System.out.println(result);
        Assert.assertEquals(3, result.size());
    }
}