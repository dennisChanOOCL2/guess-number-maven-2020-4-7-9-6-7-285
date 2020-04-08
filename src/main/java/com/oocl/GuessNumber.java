package com.oocl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuessNumber {

    private static int chance = 6;
    private String answer = "1234";

    public static int getChance() {
        return chance;
    }

    public static void setChance(int chance) {
        GuessNumber.chance = chance;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void startGame(){

    }

}
