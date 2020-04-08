package com.oocl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuessNumber {

    public static final int answerLength = 4;
    private int chance = 6;
    public static final String winMessage = "You Win !";
    public static final String loseMessage = "You Lose !";
    private String answer;


    private void setChance(int chance) {
        this.chance = chance;
    }

    public final String getAnswerForTesting() {
        return answer;
    }

    public void startGame(){
        generateAnswer();
        setChance(6);
    }

    public String guess(String input){

        String errorMessage = validInput(input);
        if (errorMessage == "") {
            if (calculateResult(input) == "4A0B") {
                return winMessage;
            }

            setChance(chance--);
            if (chance == 0) {
                return loseMessage;
            }
            return calculateResult(input);

        } else {
            return errorMessage;
        }

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

        StringBuilder answerStrBuilder = new StringBuilder(answerLength);
        answerStrBuilder.append(firstDigit).append(secondDigit).append(thirdDigit).append(FourthDigit);
        this.answer = answerStrBuilder.toString();

    }

    private String validInput(String input){
        boolean hasDuplicate = checkInputHasDuplicate(input);

        if(hasDuplicate){
            return "Wrong Input，Input again";
        }

        if (input.length() == answerLength) {
            return "";
        }
        return "Wrong Input，Input again";
    }

    private boolean checkInputHasDuplicate(String input){
        List<String> inputStringList = Arrays.asList(input.split(""));
        String checking = inputStringList.stream().filter(element -> Collections.frequency(inputStringList, element) > 1)
                .findFirst()
                .orElse("");
        if(checking.equals("")){
            return false;
        }
        return true;
    }


    private int getNumberFromList(List<Integer> numberList){
        Random random = new Random();
        int randomIndex = random.nextInt(numberList.size());
        int result = numberList.get(randomIndex);
        numberList.remove(randomIndex);
        return result;
    }
}
