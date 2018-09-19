package com.example.demo.utils;

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
                return reduction(a1 + a2, lcm).getNumber();
            } else {
                return reduction(a1 - a2, lcm).getNumber();
            }
        }
        //如果是乘法
        if (o.getOperator().equals(OperatorEnum.MULTIPLICATION.getOperator())) {
            return reduction(cf1.getNumerator() * cf2.getNumerator(), cf1.getDenominator() * cf2.getDenominator()).getNumber();
        }
        //如果是除法
        return reduction(cf1.getNumerator() * cf2.getDenominator(), cf1.getDenominator() * cf2.getNumerator()).getNumber();
    }

    /**
     * 实现约分并存入CalculateFraction
     * @param x
     * @param y
     * @return
     */
    public static CalculateFraction reduction(int x, int y) {
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
