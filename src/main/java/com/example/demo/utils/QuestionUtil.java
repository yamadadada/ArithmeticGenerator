package com.example.demo.utils;

import com.example.demo.enums.OperatorEnum;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;

import java.util.*;

public class QuestionUtil {

    /**
     * 按照String类型输出算术式
     * @param charList
     * @return
     */
    public static String charListToString(List<Char> charList, List<Parentheses> parenthesesList) {
        List<Number> numberList = fatherListToSonList(charList, Number.class);
        List<Operator> operatorList = fatherListToSonList(charList, Operator.class);
        //对list按照id进行排序
        numberList.sort(Comparator.comparing(Number::getNId));
        StringBuilder result = new StringBuilder();
        for (Number number: numberList) {
            StringBuilder s = new StringBuilder(number.getOutput());
            //添加括号
            for (Parentheses p: parenthesesList) {
                //如果有前括号
                if (p.getStartNId() == number.getNId()) {
                    s = new StringBuilder("(").append(s);
                }else if (p.getEndNId() == number.getNId()) {//如果有后括号
                    s = s.append(")");
                }
            }
            if (number.getNId() <= operatorList.size()) {
                s.append(" ").append(findCharById(operatorList, number.getNId()).getOperator()).append(" ");
            }
            result = result.append(s);
        }
        return result.toString();
    }

    /**
     * 根据id查询指定类型的Class
     * @param charList
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends Char> T findCharById(List<T> charList, int id) {
        for (T c: charList) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new RuntimeException("list超出范围！，list=" + charList + ", id=" + id);
    }

    /**
     * 从T类型的list中取出属于V类型的list
     * @param fatherList
     * @param V
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V extends T> List<V> fatherListToSonList(List<T> fatherList, Class V) {
        List<V> sonList = new LinkedList<>();
        for (T t: fatherList) {
            if (t.getClass().equals(V)) {
                sonList.add((V) t);
            }
        }
        return sonList;
    }

    /**
     * 比较两个list里的元素是否相等（不考虑顺序）
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> boolean isListEqual(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }
        for (T t1: list1) {
            if (!list2.contains(t1)) {
                return false;
            }
        }
        for (T t2: list2) {
            if (!list1.contains(t2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据id的范围查询charlist
     * @param charList
     * @param startId
     * @param endId
     * @return
     */
    public static <T extends Char> List<T> findCharById(List<T> charList, int startId, int endId) {
        List<T> result = new LinkedList<>();
        for (T c: charList) {
            if (c.getId() >= startId && c.getId() <= endId) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * 构建运算符优先运算链表
     * @param operatorList
     * @param pList
     * @return
     */
    public static List<Operator> buildLink(List<Operator> operatorList, List<Parentheses> pList) {
        if (operatorList.size() == 1) {
            return operatorList;
        }
        List<Operator> originList = new ArrayList<>();
        originList.addAll(operatorList);
        List<Parentheses> parenthesesList = new ArrayList<>();
        if (pList != null) {
            parenthesesList.addAll(pList);
        }
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
