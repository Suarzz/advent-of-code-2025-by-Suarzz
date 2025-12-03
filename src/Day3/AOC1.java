package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*!DISCLAIMER: This code is "obsolete", when solving the second part I used a more generalist
               approach that can solve both parts (and any input), which is also much cleaner.
               This is only left here to compare it with the optimized version. */

//Source code made my @Suarzz Day 3 Part 1
public class AOC1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Day3/input.txt"));
        String line;
        int result = 0;

        //To get the max possible 2 digit number in the bank, we first need to get
        //the max number (excluding the last one) to represent the 10s value, we exclude
        //the last number since it can only be a unit (1s value)
        //Ex: In this bank 4859 we first get the max number between 4 5 and 8 (in this case 8)
        //    and then we get the max number that appears AFTER the first (in this case 9)
        //    that way we get a result of 89.

        while((line = reader.readLine()) != null) {
            int[] firstDigitResult = getMaxInString(line.substring(0, line.length()-1));
            int tensDigit = firstDigitResult[0];
            int tensDigitIndex = firstDigitResult[1];

            //We only get the max, we do not care about the index
            //We start AFTER the digit we already chose for tens
            int unitsDigit = getMaxInString(line.substring(tensDigitIndex+1))[0];
            result += (tensDigit*10 + unitsDigit);
        }
        System.out.println(result);

    }

    private static int[] getMaxInString(String sequence)  {
        if(sequence.isEmpty()) return new int[] {0,0};

        int max = 0;
        int len = sequence.length();

        //Halfway through the problem I decided getting the index where we find
        //the max will be useful to create the second substring
        int maxIndex = 0;

        //We do not need to convert into int because the ascii values of
        //the characters in the string follow the same order as the digits themselves 0..9
        for(int i = 0; i<len; i++) {
            if (sequence.charAt(i) > max) {
                max = sequence.charAt(i);
                maxIndex = i;
            }
        }

        //We subtract 48 to pass from the ascii value to int value
        //Ex: 1 has the value 49 in ascii -> 49 - 48 = 1
        return new int[] {max-48,maxIndex};
    }
}