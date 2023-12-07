package Day_07;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyComparator implements Comparator<String> {
	public static Map<Character, Integer> charValuesPt1 = new HashMap<>() {
		{
			put('2', 0);
			put('3', 1);
			put('4', 2);
			put('5', 3);
			put('6', 4);
			put('7', 5);
			put('8', 6);
			put('9', 7);
			put('T', 8);
			put('J', 9);
			put('Q', 10);
			put('K', 11);
			put('A', 12);
		}
	};
	public static Map<Character, Integer> charValuesPt2 = new HashMap<>() {
		{
			put('J', 0);
			put('2', 1);
			put('3', 2);
			put('4', 3);
			put('5', 4);
			put('6', 5);
			put('7', 6);
			put('8', 7);
			put('9', 8);
			put('T', 9);
			put('Q', 10);
			put('K', 11);
			put('A', 12);
		}
	};
	
	private Map<Character, Integer> charValues;
	
	public MyComparator(Map<Character, Integer> charValues) {
		this.charValues = charValues;
	}
	

	@Override
	public int compare(String s0, String s1) {
		char[] c0 = s0.toCharArray();
		char[] c1 = s1.toCharArray();

		for (int i = 0; i < s0.length(); i++) {
			if (c0[i] != c1[i]) {
				return Integer.compare(charValues.get(c0[i]), charValues.get(c1[i]));
			}
		}
		return 0;
	}
}
