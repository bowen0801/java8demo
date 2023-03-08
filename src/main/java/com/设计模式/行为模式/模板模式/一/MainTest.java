package com.设计模式.行为模式.模板模式.一;

public class MainTest {
    public static void main(String[] args) {
        Dish eggsWithTomato = new EggsWithTomato();
        eggsWithTomato.dodish();
        System.out.println("-----------------------------");
        Dish bouilli = new Bouilli();
        bouilli.dodish();
    }
}
