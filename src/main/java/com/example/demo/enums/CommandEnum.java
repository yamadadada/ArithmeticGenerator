package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandEnum {

    N("-n", "生成题目的个数"),
    R("-r", "题目中数值的最大值"),
    E("-e", "题目文件"),
    A("-a", "答案文件"),
    ;

    private String command;

    private String msg;
}
