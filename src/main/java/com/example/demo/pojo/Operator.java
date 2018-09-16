package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 运算符
 */
@Data
@AllArgsConstructor
public class Operator implements Char{

    private int oid;

    private String operator;

    @Override
    public int getId() {
        return oid;
    }
}
