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

    private int chance = 6;
    private String answer;


    private void setChance(int chance) {
        this.chance = chance;
    }

    public final int getChance (){
        return chance;
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

    private String calculateResult(String input){
        final ArrayList<String> result = new ArrayList();
        List<String> inputStringToList = Arrays.asList(input.split(""));

        inputStringToList.stream().forEach(element ->
                        result.add(
                                input.indexOf(element) ==  answer.indexOf(element) ? CORRECT_NUMBER_AND_POSITION :
                                        answer.contains(element) ? CORRECT_NUMBER_BUT_WRONG_POSITION : ""
                        )
                );

        String returnString = Collections.frequency(result, CORRECT_NUMBER_AND_POSITION)
                + CORRECT_NUMBER_AND_POSITION
                + Collections.frequency(result, CORRECT_NUMBER_BUT_WRONG_POSITION)
                + CORRECT_NUMBER_BUT_WRONG_POSITION;

        return returnString;
    }

    private void generateAnswer(){
        List<Integer> possibleAnsList = IntStream.rangeClosed(0, 9).boxed()
                .collect(Collectors.toList());

        int firstDigit = getNumberFromList(possibleAnsList);
        int secondDigit = getNumberFromList(possibleAnsList);
        int thirdDigit = getNumberFromList(possibleAnsList);
        int FourthDigit = getNumberFromList(possibleAnsList);

        StringBuilder answerStrBuilder = new StringBuilder(ANSWER_LENGTH);
        answerStrBuilder.append(firstDigit).append(secondDigit).append(thirdDigit).append(FourthDigit);
        this.answer = answerStrBuilder.toString();

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
        List<String> inputStringList = Arrays.asList(input.split(""));
        String checking = inputStringList.stream().filter(
                element -> Collections.frequency(inputStringList, element) > 1)
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
