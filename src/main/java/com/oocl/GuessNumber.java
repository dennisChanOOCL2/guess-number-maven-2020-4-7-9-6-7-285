package com.oocl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuessNumber {

    private final int answerDigit = 4;
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

        if(calculateResult(input) == "4A0B"){
            return "You Win !";
        }

        setChance(chance--);
        if(chance == 0){
            return "You Lose !";
        }

        return calculateResult(input);
    }

    private String calculateResult(String input){

        final ArrayList<String> result = new ArrayList();
        Arrays.asList(input.split(""))
                .stream().forEach(element ->
                        result.add(
                                input.indexOf(element) ==  answer.indexOf(element) ? "A" :
                                        answer.contains(element) ? "B" : ""
                        )
                );

        String returnString = Collections.frequency(result, "A") + "A" + Collections.frequency(result, "B") + "B";
        return returnString;
    }

    private void generateAnswer(){
        List<Integer> possibleAnsList = IntStream.rangeClosed(0, 9).boxed()
                .collect(Collectors.toList());

        int firstDigit = getNumberFromList(possibleAnsList);
        int secondDigit = getNumberFromList(possibleAnsList);
        int thirdDigit = getNumberFromList(possibleAnsList);
        int FourthDigit = getNumberFromList(possibleAnsList);

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
