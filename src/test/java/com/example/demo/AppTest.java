package com.example.demo;

import org.junit.Test;

public class AppTest {

    @Test
    public void main() {
//        String[] args = {"-n", "10000", "-r", "10"};
        String[] args = {"-e", "Exercises.txt", "-a", "Answers.txt"};
        App.main(args);
    }

    @Test
    public void commandParser() {
        String[] args = {"-n", "100", "-r", "10"};
        App.commandParser(args);
    }
}