package com.example.demo.pojo;

import lombok.Data;

/**
 * 括号
 */
@Data
public class Parentheses implements Char{

    //括号里的第一个数字位置
    private int startNId;

    //括号里的最后一个数字位置
    private int endNId;

    public Parentheses(int startNId, int endNId) {
        this.startNId = startNId;
        this.endNId = endNId;
    }
}
