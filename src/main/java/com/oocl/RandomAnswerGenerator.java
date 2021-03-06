package com.oocl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomAnswerGenerator implements AnswerGenerator {
    public static final int ANSWER_LENGTH = 4;
    public static final int RANDOM_RANGE = 10;
    public static final String SPLIT_STRING_DELIMITER = "";

    public String generate() {
        Random random = new Random();
        Set<String> numbers = new HashSet<>();
        while (numbers.size() < ANSWER_LENGTH){
            numbers.add(String.valueOf(random.nextInt(RANDOM_RANGE)));
        }
        return String.join(SPLIT_STRING_DELIMITER,numbers);
    }
}
