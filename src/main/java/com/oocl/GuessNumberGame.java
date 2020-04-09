package com.oocl;

import org.mockito.Mockito;

import java.util.Scanner;

public class GuessNumberGame {
    public static void main (String[] args) {
        //given
        AnswerGenerator answerGenerator = new RandomAnswerGenerator();
        GuessNumber game = new GuessNumber(answerGenerator);
        Scanner userInputScanner = new Scanner(System.in);
        String result = "";

        System.out.println("Welcome! Game Start now !");
        while(result != game.WIN_MESSAGE && result != game.LOSE_MESSAGE){
            System.out.printf("You have %s chances(s). Please enter a 4 digit number: ", game.getChance());
            String input = userInputScanner.nextLine();
            result = game.guess(input);
            System.out.println(result);
        }

        System.out.printf("The answer is %s !", game.getAnswerForTesting());
    }

}
