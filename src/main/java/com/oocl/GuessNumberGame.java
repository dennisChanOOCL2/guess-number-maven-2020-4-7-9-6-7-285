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
            System.out.println("Please enter a 4 digit number: ");
            String input = userInputScanner.nextLine();
            result = guessNumber.guess(input);
            System.out.println(result);
        }
    }
}
