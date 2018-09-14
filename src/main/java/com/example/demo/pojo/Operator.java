package com.example.demo.pojo;

import lombok.Data;

/**
 * 运算符
 */
@Data
public class Operator implements Char{

    private int oid;

    private String operator;

    public Operator(int oid, String operator) {
        this.oid = oid;
        this.operator = operator;
    }
}
