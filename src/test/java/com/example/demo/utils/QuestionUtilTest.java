package com.example.demo.utils;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionUtilTest {

    @Test
    public void questionsToString() {
        List<Char> charList = new ArrayList<>();
        Number n1 = new Number(1, NumberTypeEnum.NATURAL_NUMBER.getCode(), 5);
        Number n2 = new Number(2, NumberTypeEnum.NATURAL_NUMBER.getCode(), 7);
        Number n3 = new Number(3, NumberTypeEnum.FRACTION.getCode(), 2, 5, 8);
        Operator o1 = new Operator(1, OperatorEnum.PLUS.getOperator());
        Operator o2 = new Operator(2, OperatorEnum.DIVISION.getOperator());
        Parentheses p = new Parentheses(2, 3);
        List<Parentheses> parenthesesList = new LinkedList<>();
        charList.add(n1);
        charList.add(n2);
        charList.add(n3);
        charList.add(o1);
        charList.add(o2);
        parenthesesList.add(p);
        String result = QuestionUtil.charListToString(charList, parenthesesList);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void findCharById() {
        List<Char> charList = new ArrayList<>();
        Number n1 = new Number(1, NumberTypeEnum.NATURAL_NUMBER.getCode(), 7);
        Number n2 = new Number(2, NumberTypeEnum.NATURAL_NUMBER.getCode(), 2);
        charList.add(n1);
        charList.add(n2);
        Char result = QuestionUtil.findCharById(charList, 2);
        Assert.assertEquals(2, result.getId());
    }

    @Test
    public void isListEqual() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(4, 2, 3, 1);
        Assert.assertEquals(true, QuestionUtil.isListEqual(list1, list2));
    }
}