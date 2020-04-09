package com.oocl;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        assertThat(result, is(guessNumber.WIN_MESSAGE));
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

    @Test
    public void calculate_one_position_correct_one_number_correct_but_postition_wrong() {
        //given
        GuessNumber guessNumber = new GuessNumber();
        guessNumber.startGame();

        String answer = guessNumber.getAnswerForTesting();
        String reserveAnswer = new StringBuilder(answer).reverse().toString();

        List<Integer> possibleAnsList = IntStream.rangeClosed(0, 9).boxed()
                .collect(Collectors.toList());

        Arrays.asList(reserveAnswer.split("")).stream().forEach(
                element -> possibleAnsList.remove((Object)Integer.parseInt(element))
        );

        StringBuilder input = new StringBuilder(guessNumber.ANSWER_LENGTH);
        input.append(answer.charAt(0)).append(reserveAnswer.charAt(0)).append(possibleAnsList.get(0)).append(possibleAnsList.get(1));

        String result = guessNumber.guess(input.toString());
        assertThat(result, is("1A1B"));
    }

    @Test
    public void invalid_input_not_enough_digit() {
        //given
        GuessNumber guessNumber = new GuessNumber();
        guessNumber.startGame();

        String result = guessNumber.guess("11");

        assertThat(result, is("Wrong Input，Input again"));
    }

    @Test
    public void invalid_input_duplicate_digit() {
        //given
        GuessNumber guessNumber = new GuessNumber();
        guessNumber.startGame();

        String result = guessNumber.guess("1124");

        assertThat(result, is("Wrong Input，Input again"));
    }

}
