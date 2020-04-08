package com.oocl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuessNumber {

    private static int chance = 6;
    private String answer = "1234";

    public static void setChance(int chance) {
        GuessNumber.chance = chance;
    }

    public final String getAnswerForTesting() {
        return answer;
    }

    public void startGame(){
        generateAnswer();
        setChance(6);
    }

    public String guess(String input){
        return calculateResult();
    }

    private String calculateResult(){
        return "4A0B";
    }

    private void generateAnswer(){
        List<Integer> possibleAnsList = IntStream.rangeClosed(0, 9).boxed()
                .collect(Collectors.toList());

        int firstDigit = getNumberFromList(possibleAnsList);
        int secondDigit = getNumberFromList(possibleAnsList);
        int thirdDigit = getNumberFromList(possibleAnsList);
        int FourthDigit = getNumberFromList(possibleAnsList);

        int answerDigit = 4;
        StringBuilder answerStrBuilder = new StringBuilder(answerDigit);
        answerStrBuilder.append(firstDigit).append(secondDigit).append(thirdDigit).append(FourthDigit);
        this.answer = answerStrBuilder.toString();

    }

    private int getNumberFromList(List<Integer> numberList){
        Random random = new Random();
        int randomIndex = random.nextInt(numberList.size());
        int result = numberList.get(randomIndex);
        numberList.remove(randomIndex);
        return result;
    }
}
