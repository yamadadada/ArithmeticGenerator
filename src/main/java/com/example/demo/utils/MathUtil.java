package com.example.demo.utils;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.CalculateFraction;
import com.example.demo.pojo.Number;
import com.example.demo.pojo.Operator;

public class MathUtil {

    public static Number calculate(Number n1, Operator o, Number n2) {
        CalculateFraction cf1 = n1.getCalculate();
        CalculateFraction cf2 = n2.getCalculate();
        //如果是加法或减法
        if (o.getOperator().equals(OperatorEnum.PLUS.getOperator()) || o.getOperator().equals(OperatorEnum.SUBTRACTION.getOperator())) {
            //最小公倍数
            int lcm = lcm(cf1.getDenominator(), cf2.getDenominator());
            int a1 = cf1.getNumerator() * (lcm / cf1.getDenominator());
            int a2 = cf2.getNumerator() * (lcm / cf2.getDenominator());
            if (o.getOperator().equals(OperatorEnum.PLUS.getOperator())) {
                Number n = reduction(a1 + a2, lcm).getNumber();
                n.setNId(o.getOid());
                return n;
            } else {
                //如果是负数，返回null
                if (a1 < a2) {
                    return null;
                } else if (a1 == a2) {
                    return new Number(o.getOid(), NumberTypeEnum.NATURAL_NUMBER.getCode(), 0);
                }
                Number n = reduction(a1 - a2, lcm).getNumber();
                n.setNId(o.getOid());
                return n;
            }
        }
        //如果是乘法
        if (o.getOperator().equals(OperatorEnum.MULTIPLICATION.getOperator())) {
            Number n = reduction(cf1.getNumerator() * cf2.getNumerator(), cf1.getDenominator() * cf2.getDenominator()).getNumber();
            n.setNId(o.getOid());
            return n;
        }
        //如果是除法
        if (cf2.getNumerator() == 0) {//如果被除数是0，返回null
            return null;
        }
        Number n = reduction(cf1.getNumerator() * cf2.getDenominator(), cf1.getDenominator() * cf2.getNumerator()).getNumber();
        n.setNId(o.getOid());
        return n;
    }

    /**
     * 实现约分并存入CalculateFraction
     * @param x
     * @param y
     * @return
     */
    public static CalculateFraction reduction(int x, int y) {
        if (x == 0 || y == 0) {
            return new CalculateFraction(x, y);
        }
        //求最大公约数
        int gcd = gcd(x, y);
        return new CalculateFraction(x / gcd, y / gcd);
    }

    /**
     * 求最小公倍数
     * @param x
     * @param y
     * @return
     */
    public static int lcm(int x, int y) {
        return x * y / gcd(x, y);
    }

    /**
     * 求最大公约数
     * @param x
     * @param y
     * @return
     */
    public static int gcd(int x, int y) {
        //判断x和y的大小
        if (x<y) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x % y == 0) {
            return y;
        } else{
            return gcd(y,x % y);//递归求最大公约数
        }
    }
}
