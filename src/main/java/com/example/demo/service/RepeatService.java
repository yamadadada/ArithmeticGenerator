package com.example.demo.service;

import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.*;
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
                    List<Operator> o1 = buildLink(QuestionUtil.fatherListToSonList(origin.getCharList(), Operator.class), origin.getParenthesesList());
                    List<Operator> o2 = buildLink(QuestionUtil.fatherListToSonList(question.getCharList(), Operator.class), question.getParenthesesList());
                    //比较运算符链表是否相同
                    for (int i = 0; i < o1.size(); i++) {
                        if (!o1.get(i).getOperator().equals(o2.get(i).getOperator())) {
                            return false;
                        }
                    }
                    //根据优先链表逐一比较
                    List<Char> charList1 = origin.getCharList();
                    List<Char> charList2 = question.getCharList();

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

    /**
     * 构建运算符优先运算链表
     * @param operatorList
     * @param parenthesesList
     * @return
     */
    public List<Operator> buildLink(List<Operator> operatorList, List<Parentheses> parenthesesList) {
        if (operatorList.size() == 1) {
            return operatorList;
        }
        List<Operator> originList = operatorList;
        List<Operator> calculateList = new LinkedList<>();
        //如果有括号，找到最优先的括号
        Parentheses priorityParentheses;
        if (parenthesesList.size() != 0) {
            priorityParentheses = parenthesesList.get(0);
            for (int i = 1; i < parenthesesList.size(); i++) {
                Parentheses p = parenthesesList.get(i);
                if (p.getEndNId() - p.getStartNId() < priorityParentheses.getEndNId() - priorityParentheses.getStartNId()) {
                    priorityParentheses = p;
                } else if (p.getEndNId() - p.getStartNId() == priorityParentheses.getEndNId() - priorityParentheses.getStartNId() && p.getStartNId() < priorityParentheses.getStartNId()) {
                    priorityParentheses = p;
                }
            }
            parenthesesList.remove(priorityParentheses);
            int start = priorityParentheses.getStartNId();
            int end = priorityParentheses.getEndNId() - 1;
            operatorList = QuestionUtil.findCharById(operatorList, start, end);
        }
        List<Operator> tempOs = new LinkedList<>();
        for (Operator o: operatorList) {
            if (o.getOperator().equals(OperatorEnum.MULTIPLICATION.getOperator()) || o.getOperator().equals(OperatorEnum.DIVISION.getOperator())) {
                ((LinkedList<Operator>) calculateList).addLast(o);
            } else {
                ((LinkedList<Operator>) tempOs).addLast(o);
            }
        }
        for (Operator o: tempOs) {
            ((LinkedList<Operator>) calculateList).addLast(o);
        }
        //删除已添加到链表的运算符
        for (Operator o: calculateList) {
            originList.remove(o);
        }
        if (originList.size() == 0) {
            return calculateList;
        } else {
            calculateList.addAll(buildLink(originList, parenthesesList));
            return calculateList;
        }
    }
}
