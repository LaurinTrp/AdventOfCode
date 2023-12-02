package Day_01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import Loaders.ResourceLoader;

public class Day_01_2022 {
	public static void main(String[] args) {
		ArrayList<String> input = ResourceLoader.getContentAsLines("2022", "Day_01/Input.txt");
		ArrayList<Integer> sums = new ArrayList<>();

		int max = 0;
		int sum = 0;

		for (int i = 0; i < input.size(); i++) {
			while (!input.get(i).strip().equals("")) {
				sum += Integer.parseInt(input.get(i));
				if (i == input.size() - 1) {
					break;
				}
				i++;
			}
			max = Math.max(max, sum);
			sums.add(sum);
			sum = 0;
		}

		Collections.sort(sums, new Comparator<Integer>() {
			@Override
			public int compare(Integer i0, Integer i1) {
				return i1 - i0;
			}
		});

		int topThree = sums.get(0) + sums.get(1) + sums.get(2);
		System.out.println(topThree);

	}
}
