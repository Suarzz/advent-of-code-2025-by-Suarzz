package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

//Source code made my @Suarzz Day 2 Part 2
public class AOC2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Day2/input.txt"));
        String line = reader.readLine();

        if (line == null) return;

        String[] ranges = line.split(",");
        long totalInvalidIDSum = 0L;

        for (String range : ranges) {
            String[] parts = range.split("-");
            long min = Long.parseLong(parts[0]);
            long max = Long.parseLong(parts[1]);

            totalInvalidIDSum += calcInvalidSum(min, max);
        }

        System.out.println(totalInvalidIDSum);
    }

    private static long calcInvalidSum(long min, long max) {
        // Using a set to avoid counting duplicates such as
        // 1111 (can be both '11' twice or '1' 4 times)
        Set<Long> foundIDs = new HashSet<>();

        String minStr = String.valueOf(min);
        String maxStr = String.valueOf(max);

        int minLen = minStr.length();
        int maxLen = maxStr.length();

        //We iterate over every possible total length
        for (int totalLen = minLen; totalLen <= maxLen; totalLen++) {

            //We have to find the possible lengths that the pattern can have:
            //This has to be divisible by the totalLen AND not bigger than
            //totalLen / 2 since we need the pattern to be repeated at least twice
            for (int k = 1; k <= totalLen / 2; k++) {

                if (totalLen % k != 0) {
                    continue;
                }

                //To do what we did in part one of splitting the number in half and then
                //creating a candidate like n * magnitude + n (Ex: 32 * 100 + 32 = 3232)
                //we need a more general formula for different pattern sizes.
                //We can find that using a "multiplier": 32 * 101 = 3232
                //We can find that multiplier with the formula below
                long totalMagnitude = powerOfTen(totalLen);
                long patternMagnitude = powerOfTen(k);

                //Using the same example before -> 3232/32 = 101
                //This applies to every number in the same length,
                //such as: 9999/99 = 101, so we can always use the pattern with 9s
                long multiplier = (totalMagnitude - 1) / (patternMagnitude - 1);

                //We determine range for the pattern itself
                long startPattern = powerOfTen(k - 1);
                long endPattern = patternMagnitude - 1;


                for (long p = startPattern; p <= endPattern; p++) {
                    long candidate = p * multiplier;

                    if (candidate >= min && candidate <= max) {
                        foundIDs.add(candidate);
                    }
                }
            }
        }

        // Sum all the found IDs in this iteration
        long sum = 0;
        for (Long id : foundIDs) {
            sum += id;
        }
        return sum;
    }

    private static long powerOfTen(int n) {
        long result = 1;
        while (n-- > 0) {
            result *= 10;
        }
        return result;
    }
}