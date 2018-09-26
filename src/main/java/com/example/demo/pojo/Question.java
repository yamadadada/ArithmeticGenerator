package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    //题号
    private int id;

    //数字和运算符的list
    private List<Char> charList;

    //括号集合
    private List<Parentheses> parenthesesList;

    //运算符优先处理链表
    private List<Operator> buildList;

    //答案
    private String result;
}
