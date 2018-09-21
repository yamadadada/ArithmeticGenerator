package com.example.demo;

import org.junit.Test;

public class AppTest {

    @Test
    public void main() {
        String[] args = {"-n", "100", "-r", "10"};
        App.main(args);
    }

    @Test
    public void commandParser() {
        String[] args = {"-n", "100", "-r", "10"};
        App.commandParser(args);
    }
}