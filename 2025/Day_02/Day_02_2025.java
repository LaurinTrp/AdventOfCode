package Day_02;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import Globals.ResourceLoader;

public class Day_02_2025 {
	public static void main(String[] args) {
		AtomicLong result1 = new AtomicLong();
		AtomicLong result2 = new AtomicLong();
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_02" + File.separator + "Input.txt");

		String input = lines.get(0);
		
		String pattern1 = "^(.+)(?:\\1){1}$";
		String pattern2 = "^(.+)(?:\\1)+$";

		String[] idRanges = input.split(",");
		long[][] idRangesSplit = Arrays.stream(idRanges).map(s -> s.split("-"))
				.map(arr -> new long[] { Long.parseLong(arr[0]), Long.parseLong(arr[1]) }).toArray(long[][]::new);
		Arrays.stream(idRangesSplit).parallel().forEach(arr -> {

			for (long i = arr[0]; i <= arr[1]; i++) {
				String value = "" + i;
				if(value.matches(pattern2)) {
					result2.addAndGet(i);
				}
				if(value.length() % 2 != 0) {
					continue;
				}
				
				if(value.matches(pattern1)) {
					result1.addAndGet(i);
				}
			}

		});

		System.out.println("Result 1: " + result1.get());
		System.out.println("Result 2: " + result2.get());
	}
}
