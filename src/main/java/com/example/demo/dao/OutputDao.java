package com.example.demo.dao;

import com.example.demo.pojo.Question;
import com.example.demo.utils.QuestionUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OutputDao {

    public void toExercises(List<Question> questionList) {
        File file1 = new File("Exercises.txt");
        File file2 = new File("Answers.txt");
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        try {
            bw1 = new BufferedWriter(new FileWriter(file1));
            bw2 = new BufferedWriter(new FileWriter(file2));
            for (Question question: questionList) {
                bw1.write(question.getId() + ". " + QuestionUtil.charListToString(question.getCharList(), question.getParenthesesList()) + " = ");
                bw1.newLine();
                bw2.write(question.getId() + ". " + question.getResult());
                bw2.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("文件输出错误！");
        } finally {
            try {
                bw1.flush();
                bw2.flush();
                bw1.close();
                bw2.close();
            } catch (IOException e) {
                throw new RuntimeException("输出流关闭错误！");
            }
        }
    }

    public void correct(String fileName1, String fileName2) {
        File file1 = new File(fileName1);
        File file2 = new File(fileName2);
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        List<String> correct = new ArrayList<>();
        List<String> wrong = new ArrayList<>();
        try {
            br1 = new BufferedReader(new FileReader(file1));
            br2 = new BufferedReader(new FileReader(file2));
            String s1;
            String s2;
            while ((s1 = br1.readLine()) != null) {
                String[] ssss = s1.split("\\.");
                String questionNO = s1.split("\\.")[0];
                if ((s2 = br2.readLine()) != null) {
                    String answer = s1.split(" =")[1].replace(" ", "");
                    String result = s2.split("\\.")[1].replace(" ", "");
                    if (answer.equals(result)) {
                        correct.add(questionNO);
                    }else {
                        wrong.add(questionNO);
                    }
                } else {
                    System.out.println("题号为：" + questionNO + "的答案不存在！");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            try {
                br1.close();
                br2.close();
            } catch (IOException e) {
                throw new RuntimeException("输入流关闭错误！");
            }
        }

        StringBuilder sb1 = new StringBuilder("Correct: ");
        sb1.append(correct.size());
        sb1.append("(");
        for (String s: correct) {
            sb1.append(s);
            sb1.append(", ");
        }
        sb1 = sb1.replace(sb1.length() - 2, sb1.length(), "");
        sb1.append(")");

        StringBuilder sb2 = new StringBuilder("Wrong: ");
        sb2.append(wrong.size());
        sb2.append("(");
        for (String s: wrong) {
            sb2.append(s);
            sb2.append(", ");
        }
        sb2 = sb2.replace(sb2.length() - 2, sb2.length(), "");
        sb2.append(")");

        File file = new File("Grade.txt");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb1.toString());
            bw.newLine();
            bw.write(sb2.toString());
        } catch (IOException e) {
            throw new RuntimeException("文件输出错误！");
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException("输出流关闭错误！");
            }
        }
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
    }
}
