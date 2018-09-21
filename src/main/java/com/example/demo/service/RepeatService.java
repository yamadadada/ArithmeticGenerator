package com.example.demo.service;

import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;
import com.example.demo.utils.MathUtil;
import com.example.demo.utils.QuestionUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RepeatService {

    /**
     * 检查题目是否与已有题目重复
     * @param questionList
     * @param question
     * @return
     */
    public boolean isRepeat(List<Question> questionList, Question question) {
        for (Question origin: questionList) {
            //先检查题目答案是否相同再进行下一步
            if (origin.getResult().equals(question.getResult())) {
                //检查运算符的个数和种类是否相同
                if (isOperatorRepeat(QuestionUtil.fatherListToSonList(origin.getCharList(), Operator.class), QuestionUtil.fatherListToSonList(question.getCharList(), Operator.class))) {
                    //只有一个运算符说明重复
                    if (QuestionUtil.fatherListToSonList(question.getCharList(), Operator.class).size() == 1) {
                        return true;
                    }
                    //构建运算符优先链表
                    List<Operator> o1 = QuestionUtil.buildLink(QuestionUtil.fatherListToSonList(origin.getCharList(), Operator.class), origin.getParenthesesList());
                    List<Operator> o2 = QuestionUtil.buildLink(QuestionUtil.fatherListToSonList(question.getCharList(), Operator.class), question.getParenthesesList());
                    //比较运算符优先链表是否相同
                    for (int i = 0; i < o1.size(); i++) {
                        if (!o1.get(i).getOperator().equals(o2.get(i).getOperator())) {
                            return false;
                        }
                    }
                    //根据优先链表逐一比较
                    List<Char> charList1 = new LinkedList<>();
                    List<Char> charList2 = new LinkedList<>();
                    charList1.addAll(origin.getCharList());
                    charList2.addAll(question.getCharList());
                    List<String> stringList1 = new LinkedList<>();
                    List<String> stringList2 = new LinkedList<>();
                    for (int i = 0; i < o1.size() - 1; i ++) {
                        int index1 = charList1.indexOf(o1.get(i));
                        int index2 = charList2.indexOf(o2.get(i));
                        stringList1.add(((Number)charList1.get(index1 - 1)).getOutput());
                        stringList1.add(((Number)charList1.get(index1 + 1)).getOutput());
                        stringList2.add(((Number)charList2.get(index2 - 1)).getOutput());
                        stringList2.add(((Number)charList2.get(index2 + 1)).getOutput());
                        if (!QuestionUtil.isListEqual(stringList1, stringList2)) {
                            return false;
                        }
                        stringList1.clear();
                        stringList2.clear();
                        Number n = MathUtil.calculate((Number)charList1.get(index1 - 1), o1.get(i), (Number)charList1.get(index1 + 1));
                        for (int j = 0; j < 3; j++) {
                            charList1.remove(index1 - 1);
                            charList2.remove(index2 - 1);
                        }
                        charList1.add(index1 - 1, n);
                        charList2.add(index2 - 1, n);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOperatorRepeat(List<Operator> operatorList1, List<Operator> operatorList2) {
        if (!(operatorList1.size() == operatorList2.size())) {
            return false;
        }
        List<String> stringList1 = operatorList1.stream().map(e -> e.getOperator()).collect(Collectors.toList());
        List<String> stringList2 = operatorList2.stream().map(e -> e.getOperator()).collect(Collectors.toList());
        return QuestionUtil.isListEqual(stringList1, stringList2);
    }
}
