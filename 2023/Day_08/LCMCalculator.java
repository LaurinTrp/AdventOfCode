package Day_08;

import java.util.Arrays;

public class LCMCalculator {
    // Function to calculate GCD
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Function to calculate LCM for two values
    private static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    // Function to calculate LCM for multiple values
    public static int calculateLCM(int[] values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("At least two values are required");
        }

        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            result = lcm(result, values[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] values = {4, 6, 8, 10};
        int lcmResult = calculateLCM(values);
        System.out.println("LCM of " + Arrays.toString(values) + " is: " + lcmResult);
    }
}
