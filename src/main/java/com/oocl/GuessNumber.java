package com.oocl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GuessNumber {

    public static final int ANSWER_LENGTH = 4;
    public static final String WIN_MESSAGE = "You Win !";
    public static final String LOSE_MESSAGE = "You Lose !";
    public static final String ERROR_MESSAGE = "Wrong Input，Input again";
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
        AtomicInteger positionAndNumberCorrectCount = new AtomicInteger (0);
        AtomicInteger  onlyNumberCorrectCount = new AtomicInteger (0);

        Arrays.asList(inputNumbers.split("")).forEach(number ->
                {
                    boolean isPositionAndNumberCorrect = this.answer.contains(number)
                            && this.answer.indexOf(number) == inputNumbers.indexOf(number);

                    boolean isNumberCorrectWithWrongPosition = this.answer.contains(number)
                            && this.answer.indexOf(number) != inputNumbers.indexOf(number);

                    if(isPositionAndNumberCorrect){
                        positionAndNumberCorrectCount.getAndIncrement() ;
                    }
                    if(isNumberCorrectWithWrongPosition){
                        onlyNumberCorrectCount .getAndIncrement() ;
                    }
                });

        return String.format(ANSWER_RESULT_PATTERN, positionAndNumberCorrectCount, onlyNumberCorrectCount);
    }

    private String validInput(String input){
        boolean hasDuplicate = checkInputHasDuplicate(input);
        boolean validLength = (input.length() == ANSWER_LENGTH);
        if(hasDuplicate
                || !isNumeric(input)
                || !validLength){
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
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
