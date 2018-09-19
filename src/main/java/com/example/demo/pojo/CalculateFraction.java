package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用于计算的分数类
 */
@Data
@AllArgsConstructor
public class CalculateFraction {

    private int numerator;

    private int denominator;
}
