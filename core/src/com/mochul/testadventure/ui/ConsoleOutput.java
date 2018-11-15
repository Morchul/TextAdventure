package com.mochul.testadventure.ui;

public class ConsoleOutput implements Output {

    @Override
    public void printPlaceText(String text) {
        System.out.println(text);
    }

    @Override
    public void printPositionText(String text) {
        System.out.println(text);
    }

    @Override
    public void printInfoText(String text) {
        System.out.println(text);
    }
}
