package com.example.demo.service;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.Char;
import com.example.demo.pojo.Number;
import com.example.demo.pojo.Operator;
import com.example.demo.pojo.Parentheses;
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
        Parentheses p1 = new Parentheses(1, 3, 4);
        Parentheses p2 = new Parentheses(2, 1, 2);
        parenthesesList.add(p1);
        parenthesesList.add(p2);
        List<Operator> result = repeatService.buildLink(operatorList, parenthesesList);
        System.out.println(result);
        Assert.assertEquals(3, result.size());
    }
}