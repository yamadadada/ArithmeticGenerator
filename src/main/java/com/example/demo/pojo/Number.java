package com.example.demo.pojo;

import com.example.demo.enums.NumberTypeEnum;
import lombok.Data;

/**
 * 自然数或分数
 */
@Data
public class Number implements Char{

    private int nId;

    //数字的类型（整数/分数）
    private int numberType;

    //自然数/带分数的整数部分
    private int number;

    //分子
    private int numerator;

    //分母
    private int denominator;

    public String getOutput() {
        //如果是分数
        if (numberType == NumberTypeEnum.FRACTION.getCode()) {
            if (number == 0) {
                return numerator + "/" + denominator;
            } else {
                return number + "'" + numerator + "/" + denominator;
            }
        } else {//如果是自然数
            return String.valueOf(number);
        }
    }

    public CalculateFraction getCalculate() {
        return new CalculateFraction(number * denominator + numerator, denominator);
    }

    public Number(int nId, int numberType, int number) {
        this.nId = nId;
        this.numberType = numberType;
        this.number = number;
        this.numerator = 0;
        this.denominator = 1;
    }

    public Number(int nId, int numberType, int number, int numerator, int denominator) {
        this.nId = nId;
        this.numberType = numberType;
        this.number = number;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public int getId() {
        return nId;
    }

    @Override
    public String toString() {
        return getOutput();
    }
}
