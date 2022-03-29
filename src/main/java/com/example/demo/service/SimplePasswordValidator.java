package com.example.demo.service;

public class SimplePasswordValidator implements PasswordValidator {

    @Override
    public boolean isValid(String password) {
        return false;
    }
}
