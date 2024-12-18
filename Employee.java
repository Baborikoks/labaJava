package com.example.demo13;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Для сериализации
    private int number;
    private String fullName;
    private String position;

    public Employee(int number, String fullName, String position) {
        this.number = number;
        this.fullName = fullName;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return number + "," + fullName + "," + position; // Формат CSV
    }
}