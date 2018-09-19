package com.example.demo.utils;

import com.example.demo.enums.NumberTypeEnum;
import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.CalculateFraction;
import com.example.demo.pojo.Char;
import com.example.demo.pojo.Number;
import com.example.demo.pojo.Operator;

import java.util.List;

public class MathUtil {

    public static String calculate(List<Char> charList) {
        return null;
    }

    public static Number calculate(Number n1, Operator o, Number n2) {
//        if (o.getOperator().equals(OperatorEnum.PLUS.getOperator())) {
//            if (n1.getNumberType() == NumberTypeEnum.NATURAL_NUMBER.getCode() && n2.getNumberType() == NumberTypeEnum.NATURAL_NUMBER.getCode()) {
//                int result = n1.getNumber() + n2.getNumber();
//                return new Number();
//            } else {
//                CalculateFraction cf1 = n1.getCalculate();
//                CalculateFraction cf2 = n2.getCalculate();
//                //最小公倍数
//                int lcm = cf1.getDenominator() * cf2.getDenominator() / gcd(cf1.getDenominator(), cf2.getDenominator());
//                int a1 = cf1.getNumerator() * (lcm / cf1.getDenominator());
//                int a2 = cf2.getNumerator() * (lcm / cf2.getDenominator());
//                new CalculateFraction(a1 + a2, lcm);
//            }
//        }
        return null;
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
