package com.oocl;

import java.util.Scanner;

public class GuessNumberGame {

    public static void main (String[] args) {
        //given
        GuessNumber guessNumber = new GuessNumber();
        guessNumber.startGame();
        String result = "";
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Welcome! Game Start now !");

        while(result != guessNumber.winMessage && result != guessNumber.loseMessage){
            System.out.printf("You have %s chances(s). Please enter a 4 digit number: ", guessNumber.getChance());
            String input = userInputScanner.nextLine();
            result = guessNumber.guess(input);
            System.out.println(result);
        }
        
        System.out.printf("The answer is %s !", guessNumber.getAnswerForTesting());
    }
}
