package Day_03;

import java.io.File;
import java.util.List;
import Globals.ResourceLoader;

public class Day_03_2025 {
	public static void main(String[] args) {
		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_03" + File.separator + "Input.txt");

		for (String line : lines) {
			int[] bank = line.chars().map(c -> c - '0').toArray();
			int index = 0;
			int highest0 = 0;
			for (int i = 0; i < bank.length - 1; i++) {
				if (bank[i] > highest0) {
					highest0 = bank[i];
					index = i;
				}
			}

			int highest1 = 0;
			for (int i = index + 1; i < bank.length; i++) {
				if (bank[i] > highest1) {
					highest1 = bank[i];
				}
			}

			result1 += highest0 * 10 + highest1;
		}

		System.out.println("Result 1: " + result1);

		for (String line : lines) {
			int[] bank = line.chars().map(c -> c - '0').toArray();

		    int n = bank.length;
		    int k = 12;
		    int[] highests = new int[k];

		    int start = 0;

		    for (int i = 0; i < k; i++) {
		        int remaining = k - i;      

		        int end = n - remaining;

		        int maxDigit = -1;
		        int maxIndex = start;

		        for (int j = start; j <= end; j++) {
		            if (bank[j] > maxDigit) {
		                maxDigit = bank[j];
		                maxIndex = j;
		            }
		        }

		        highests[i] = maxDigit;
		        start = maxIndex + 1;
		    }
		    
		    long result = 0;
		    for (int value : highests) {
				result = result * 10 + value;
			}
		    result2 += result;
		}

		System.out.println("Result 2: " + result2);
	}
}
