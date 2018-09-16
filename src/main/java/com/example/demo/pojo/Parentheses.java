package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 括号
 */
@Data
@AllArgsConstructor
public class Parentheses implements Char{

    private int pid;

    //括号里的第一个数字位置
    private int startNId;

    //括号里的最后一个数字位置
    private int endNId;

    @Override
    public int getId() {
        return pid;
    }
}
