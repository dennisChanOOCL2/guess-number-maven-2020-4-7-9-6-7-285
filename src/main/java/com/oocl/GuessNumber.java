package com.oocl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuessNumber {

    public static final int ANSWER_LENGTH = 4;
    public static final String WIN_MESSAGE = "You Win !";
    public static final String LOSE_MESSAGE = "You Lose !";
    public static final String ERROR_MESSAGE = "Wrong Input，Input again";
    public static final String CORRECT_NUMBER_AND_POSITION = "A";
    public static final String CORRECT_NUMBER_BUT_WRONG_POSITION = "B";
    public static final String WIN_RESULT = "4A0B";
    public static final String ANSWER_RESULT_PATTERN = "%sA%sB";

    private int chance = 6;
    private final String answer;

    public GuessNumber(AnswerGenerator answerGenerator) {
        this.answer = answerGenerator.generate();
        setChance(6);
    }

    private void setChance(int chance) {
        this.chance = chance;
    }

    public final int getChance (){
        return chance;
    }

    public final String getAnswerForTesting() {
        return answer;
    }

    public String guess(String input){

        String errorMessage = validInput(input);

        if(errorMessage != ""){
            return errorMessage;
        }

        String resultMessage = calculateResult(input);
        String returnMessage = checkWinLose(resultMessage);
        return returnMessage;

    }

    private String checkWinLose(String resultMessage){
        if (resultMessage.equals(WIN_RESULT)) {
            return WIN_MESSAGE;
        }

        setChance(chance-1);

        if (chance == 0) {
            return LOSE_MESSAGE;
        }

        return resultMessage;
    }

    protected String calculateResult(String inputNumbers){
        int positionAndNumberCorrectCount = 0;
        int onlyNumberCorrectCount = 0;

        for(char number : inputNumbers.toCharArray()){
            boolean isPositionAndNumberCorrect = this.answer.contains(Character.toString(number))
                    && this.answer.indexOf(number) == inputNumbers.indexOf(number);

            boolean isNumberCorrectWithWrongPosition = this.answer.contains(Character.toString(number))
                    && this.answer.indexOf(number) != inputNumbers.indexOf(number);

            if(isPositionAndNumberCorrect){
                positionAndNumberCorrectCount ++ ;
            }
            if(isNumberCorrectWithWrongPosition){
                onlyNumberCorrectCount ++ ;
            }
        }

        return String.format(ANSWER_RESULT_PATTERN, positionAndNumberCorrectCount, onlyNumberCorrectCount);
    }

    private String validInput(String input){
        boolean hasDuplicate = checkInputHasDuplicate(input);

        if(!isNumeric(input)
                || hasDuplicate
                || input.length() != ANSWER_LENGTH){
            return ERROR_MESSAGE;
        }

        return "";
    }

    private boolean checkInputHasDuplicate(String input){
        List<String> inputStringToList = Arrays.asList(input.split(""));
        String checking = inputStringToList.stream().filter(
                element -> Collections.frequency(inputStringToList, element) > 1)
                .findFirst()
                .orElse("");
        if(checking.equals("")){
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double number = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
