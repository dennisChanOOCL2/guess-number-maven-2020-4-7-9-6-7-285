package com.oocl;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GuessNumberPracticeTest {

    @Test
    public void should_return_4A0B() {
        //given
        GuessNumberPractice guessNumber = new GuessNumberPractice("1234");

        String result = guessNumber.guess("1234");

        assertThat(result, is("4A0B"));
    }

}
