package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Source code made my @Suarzz Day 3 Part 2
public class AOC2 {
    private static final int TOTAL_BATTERIES = 12;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Day3/input.txt"));
        String line;
        long result = 0;

        while((line = reader.readLine()) != null) {
            int left = 0;
            for (int i = TOTAL_BATTERIES-1; i>=0; i--) {

                //We send the elegible numbers to represent the next digit.
                //If for example we need to represent the 10s, we can't use the last digit
                //because then we wouldn't have more digits to represent the units, so we exclude them
                int right = line.length()-i;

                int maxIndex = getMaxInString(line, left, line.length()-i);

                //We subtract 48 to pass from the ascii value to int value
                //Ex: 1 has the value 49 in ascii -> 49 - 48 = 1
                long partialResult = line.charAt(maxIndex) - 48L;
                result += powerOfTen(i) * partialResult;

                left = maxIndex+1;
            }
        }
        System.out.println(result);
    }


    private static int getMaxInString(String sequence, int left, int right)  {
        if(sequence.isEmpty()) return -1;

        long max = 0;

        //Halfway through the problem I decided getting the index where we find
        //the max will be useful to create the second substring
        int maxIndex = 0;

        //We do not need to convert into int because the ascii values of
        //the characters in the string follow the same order as the digits themselves 0..9
        for(int i = left; i<right; i++) {
            if (sequence.charAt(i) > max) {
                max = sequence.charAt(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private static long powerOfTen(int n) {
        long result = 1;
        while (n-- > 0) {
            result *= 10;
        }
        return result;
    }
}
