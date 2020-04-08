package com.oocl;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class GuessNumberTest {

    @Test
    public void calculate_all_number_and_position_correct() {
        //given
        GuessNumber guessNumber = new GuessNumber();
        guessNumber.startGame();

        String answer = guessNumber.getAnswerForTesting();
        String result = guessNumber.guess(answer);

        assertThat(result, is("4A0B"));
    }

    @Test
    public void calculate_all_number_correct_but_all_position_wrong() {
        //given
        GuessNumber guessNumber = new GuessNumber();
        guessNumber.startGame();

        String answer = guessNumber.getAnswerForTesting();
        String reserveAnswer = new StringBuilder(answer).reverse().toString();
        String result = guessNumber.guess(reserveAnswer);

        assertThat(result, is("0A4B"));
    }

}
