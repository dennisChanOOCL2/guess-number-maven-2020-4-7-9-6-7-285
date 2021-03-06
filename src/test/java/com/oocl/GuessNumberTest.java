package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class GuessNumberTest {
    private GuessNumber game;


    @Before
    public void setUp() throws Exception {
        // before method
        String answer = "1234";
        AnswerGenerator answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(answer);
        game = new GuessNumber(answerGenerator);

    }



    @Test
    public void should_return_4A0B_when_answer_is_1234_given_input_is_1234() {
        String inputNumbers = "1234";
        String result = game.guess(inputNumbers);

        Assert.assertEquals(game.WIN_MESSAGE, result);
    }

    @Test
    public void should_return_2A2B_when_answer_is_1234_given_input_is_1243() {
        String inputNumbers = "1243";
        String result = game.guess(inputNumbers);

        Assert.assertEquals("2A2B", result);
    }

    @Test
    public void should_return_1A1B_when_answer_is_1234_given_input_is_1356() {
        String inputNumbers = "1356";
        String result = game.guess(inputNumbers);

        Assert.assertEquals("1A1B", result);
    }

    @Test
    public void should_return_0A4B_when_answer_is_1234_given_input_is_4321() {
        String inputNumbers = "4321";
        String result = game.guess(inputNumbers);

        Assert.assertEquals("0A4B", result);
    }

    @Test
    public void should_return_1A1B_when_answer_is_1234_given_input_is_5167() {
        String inputNumbers = "5167";
        String result = game.guess(inputNumbers);

        Assert.assertEquals("0A1B", result);
    }

    @Test
    public void should_return_0A0B_when_answer_is_1234_given_input_is_5678() {
        String inputNumbers = "5678";
        String result = game.guess(inputNumbers);

        Assert.assertEquals("0A0B", result);
    }

    @Test
    public void invalid_input_not_enough_digitgiven_input_is_11() {
        //given


        String result = game.guess("11");

        assertThat(result, is("Wrong Input，Input again"));
    }

    @Test
    public void invalid_input_duplicate_digit() {
        //given

        String result = game.guess("1124");
        assertThat(result, is("Wrong Input，Input again"));

    }



}
