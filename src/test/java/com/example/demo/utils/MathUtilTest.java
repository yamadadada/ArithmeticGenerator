package com.example.demo.utils;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.Number;
import com.example.demo.pojo.Operator;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathUtilTest {

    @Test
    public void calculate() {
        Number n1 = new Number(0, NumberTypeEnum.NATURAL_NUMBER.getCode(), 5);
        Number n2 = new Number(0, NumberTypeEnum.FRACTION.getCode(), 0, 1, 3);
        Operator o = new Operator(0, OperatorEnum.DIVISION.getOperator());
        Number n = MathUtil.calculate(n1, o, n2);
        System.out.println(n.getOutput());
    }

    @Test
    public void reduction() {
    }

    @Test
    public void lcm() {
    }

    @Test
    public void gcd() {
    }
}