package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Source code made my @Suarzz Day 1 Part 2
public class AOC2 {

    private static final int DIAL_SIZE = 100;
    private static final int STARTING_NUM = 50;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Day1/input.txt"));
        String line;

        int currentNum = STARTING_NUM;
        int zeroCount = 0;
        boolean startedOnZero = false;

        while((line = reader.readLine()) != null) {
            int lineValue = Integer.parseInt(line.substring(1));
            switch (line.charAt(0)) {
                case 'R' -> currentNum += lineValue;

                case 'L' -> currentNum -= lineValue;

                default -> throw new InvalidPropertiesFormatException("Invalid input format");
            }

            //If the number is negative and we DID NOT start in zero, we passed zero at least once
            if (currentNum <= 0) {
                if(!startedOnZero) zeroCount++;
                zeroCount = zeroCount + (-currentNum / DIAL_SIZE);
            } else {
                zeroCount = zeroCount + (currentNum / DIAL_SIZE);
            }

            //If the number is negative, we sum 100 to get the correct positive modulo result
            currentNum = currentNum >= 0 ? currentNum % DIAL_SIZE : ((currentNum % DIAL_SIZE) + DIAL_SIZE) % DIAL_SIZE;

            startedOnZero = currentNum == 0;

        }
        System.out.println("The password is " + zeroCount);
    }
}
