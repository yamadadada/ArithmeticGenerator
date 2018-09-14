package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    //题号
    private int id;

    private List<Char> charList;

    //答案
    private Number result;
}
