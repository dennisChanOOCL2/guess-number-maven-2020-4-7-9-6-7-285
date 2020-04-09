package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsoleInputReaderTest {

    @Test
    public void should_return_input_when_get_inpt() {
        ConsoleInputReader reader = new ConsoleInputReader();
        String expected = "1234";
        InputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        String input = reader.getInput();
        Assert.assertEquals(expected, input);
    }

}
