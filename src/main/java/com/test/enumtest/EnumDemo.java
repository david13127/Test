package main.java.com.test.enumtest;

public class EnumDemo {
    public static void main(String[] args) {
        for (Apple apple : Apple.values()) {
            System.out.println(Apple.valueOf(apple.name()));
            System.out.println(apple.name());
            System.out.println(apple.getColor());
            System.out.println(apple.getPrice());
        }
    }
}
