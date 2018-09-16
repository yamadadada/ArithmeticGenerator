package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void main() {
    }

    @Test
    public void commandParser() {
        String[] args = {"-n", "100", "-r", "10"};
        App.commandParser(args);
    }
}