package com.example.demo.pojo;

import com.example.demo.enums.NumberTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用于计算的分数类
 */
@Data
@AllArgsConstructor
public class CalculateFraction {

    //分子
    private int numerator;

    //分母
    private int denominator;

    public Number getNumber() {
        if (denominator == 1) {
            return new Number(0, NumberTypeEnum.NATURAL_NUMBER.getCode(), numerator);
        }
        if (numerator > denominator) {
            return new Number(0, NumberTypeEnum.FRACTION.getCode(), (numerator - (numerator % denominator)) / denominator, numerator % denominator, denominator);
        } else if (numerator == denominator) {
            return new Number(0, NumberTypeEnum.NATURAL_NUMBER.getCode(), 1);
        } else {
            return new Number(0, NumberTypeEnum.FRACTION.getCode(), 0, numerator, denominator);
        }
    }
}
