package com.oocl;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
