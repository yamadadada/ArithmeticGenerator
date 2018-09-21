package com.example.demo.pojo;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

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
