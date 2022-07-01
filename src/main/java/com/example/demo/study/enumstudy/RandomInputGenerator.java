package com.example.demo.study.enumstudy;

public class RandomInputGenerator implements Generator<Input>{
    @Override
    public Input next() {
        return Input.randomSelection();
    }
}
