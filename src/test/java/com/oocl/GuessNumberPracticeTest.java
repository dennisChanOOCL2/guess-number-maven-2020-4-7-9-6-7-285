package com.oocl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GuessNumberPracticeTest {

    private GuessNumberPractice game;

    @Before
    public void setUp(){
        String answer = "1234";
        AnswerGenerator answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(answer);
        game = new GuessNumberPractice(answerGenerator);
    }


    @Test
    public void should_return_4A0B_when_answer_is_1234_given_input_is_1234() {
        //given
        String inputNumber = "1234";
        String result = game.guess(inputNumber);

        assertThat(result, is("4A0B"));
    }

    @Test
    public void should_return_2A2B_when_answer_is_1234_given_input_is_1243() {
        //given
        String inputNumber = "1243";
        String result = game.guess(inputNumber);

        assertThat(result, is("2A2B"));
    }

}
