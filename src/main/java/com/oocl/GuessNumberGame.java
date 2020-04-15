package com.oocl;

import java.util.Scanner;

public class GuessNumberGame {

    public static final String WELCOME_GAME_START_NOW = "Welcome! Game Start now !";
    public static final String SHOW_REMAIN_CHANCES_AND_GAME_MESSAGE_TO_USER = "You have %s chances(s). Please enter a 4 digit number: ";
    public static final String SHOW_ANSWER_MESSAGES = "The answer is %s !";

    public static void main (String[] args) {
        //given
        AnswerGenerator answerGenerator = new RandomAnswerGenerator();
        GuessNumber game = new GuessNumber(answerGenerator);
        Scanner userInputScanner = new Scanner(System.in);
        String result = "";

        System.out.println(WELCOME_GAME_START_NOW);
        while(result != game.WIN_MESSAGE && result != game.LOSE_MESSAGE){
            System.out.printf(SHOW_REMAIN_CHANCES_AND_GAME_MESSAGE_TO_USER, game.getChance());
            String input = userInputScanner.nextLine();
            result = game.guess(input);
            System.out.println(result);
        }

        System.out.printf(SHOW_ANSWER_MESSAGES, game.getAnswerForTesting());
    }

}
