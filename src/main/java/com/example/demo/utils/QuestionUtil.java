package com.example.demo.utils;

import com.example.demo.pojo.*;
import com.example.demo.pojo.Number;

import java.util.*;

public class QuestionUtil {

    /**
     * 按照String类型输出算术式
     * @param charList
     * @return
     */
    public static String charListToString(List<Char> charList) {
        List<Number> numberList = fatherListToSonList(charList, Number.class);
        List<Operator> operatorList = fatherListToSonList(charList, Operator.class);
        List<Parentheses> parenthesesList = fatherListToSonList(charList, Parentheses.class);
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
}
