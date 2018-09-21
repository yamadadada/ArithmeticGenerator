package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 括号
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parentheses{

    //括号里的第一个数字位置
    private int startNId;

    //括号里的最后一个数字位置
    private int endNId;
}
