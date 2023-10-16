package ru.aston.nikolaev.hometask3.dto;

import java.util.StringJoiner;

public class Auto {
    private int id;
    private String type;
    private String color;
    private int number;

    public Auto(String type, String color, int number) {
        this.type = type;
        this.color = color;
        this.number = number;
    }

    public Auto() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Auto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type='" + type + "'")
                .add("color='" + color + "'")
                .add("number=" + number)
                .toString();
    }
}
