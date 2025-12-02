package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Day2/input.txt"));
        String line = reader.readLine();
        String[] ranges = line.split(",");

        long invalidIDSum = 0L;

        for (String range : ranges) {
            String[] currentRange = range.split("-");
            invalidIDSum += calcPartialSum(currentRange[0], currentRange[1]);
        }

        System.out.println(invalidIDSum);
    }

    private static long calcPartialSum(String min, String max) {
        long minLong = Long.parseLong(min);
        long maxLong = Long.parseLong(max);

        int minLength = min.length();
        int maxLength = max.length();

        long result = 0;

        //We check every possible length between the range
        for (int len = minLength; len <= maxLength; len++) {

            // We only care about even lengths (e.g., 2, 4, 6...)
            if (len % 2 != 0) {
                continue;
            }

            int halfLen = len / 2;
            long magnitude = powerOfTen(halfLen);

            // Calculate the range of the "half" number for this specific length
            // Standard range for half-length 'n' is 10^(n-1) to 10^n - 1
            // Example: for length 4, half is 2 digits. Standard range: 10 to 99.
            long startHalf = powerOfTen(halfLen - 1);
            long endHalf = magnitude - 1;

            // If we are at the very bottom length, the startHalf cannot be lower than the Input Min
            if (len == minLength) {
                long inputStartHalf = minLong / magnitude;
                if (inputStartHalf > startHalf) {
                    startHalf = inputStartHalf;
                }
            }

            // If we are at the very top length, the endHalf cannot be higher than the Input Max
            if (len == maxLength) {
                long inputEndHalf = maxLong / magnitude;
                if (inputEndHalf < endHalf) {
                    endHalf = inputEndHalf;
                }
            }

            // Iterate through the halves and construct the palindrome
            for (long h = startHalf; h <= endHalf; h++) {
                long candidateID = h * magnitude + h;

                // Final check to ensure we didn't go slightly out of bounds
                if (candidateID >= minLong && candidateID <= maxLong) {
                    result += candidateID;
                }
            }
        }
        return result;
    }

    private static long powerOfTen(int n) {
        long result = 1;
        while (n-- > 0) {
            result *= 10;
        }
        return result;
    }
}
