package Day_03;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Globals.ResourceLoader;

public class Day_03_2024 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_03" + File.separator + "Input.txt");

		int result1 = 0;

		for (String line : lines) {
			result1 += getProducts(line);
		}

		System.out.println("Result 1: " + result1);

		String input = "";
		for (String line : lines) {
			input += line;
		}

		String mulRegex = "mul\\((\\d+),(\\d+)\\)";
		String doRegex = "do\\(\\)";
		String dontRegex = "don't\\(\\)";

		Pattern mulPattern = Pattern.compile(mulRegex);
		Pattern doPattern = Pattern.compile(doRegex);
		Pattern dontPattern = Pattern.compile(dontRegex);

		boolean isEnabled = true;
		int result2 = 0;

		int index = 0;
		while (index < input.length()) {
			Matcher doMatcher = doPattern.matcher(input.substring(index));
			Matcher dontMatcher = dontPattern.matcher(input.substring(index));
			Matcher mulMatcher = mulPattern.matcher(input.substring(index));

			int nextDo = doMatcher.find() ? doMatcher.start() : Integer.MAX_VALUE;
			int nextDont = dontMatcher.find() ? dontMatcher.start() : Integer.MAX_VALUE;
			int nextMul = mulMatcher.find() ? mulMatcher.start() : Integer.MAX_VALUE;

			if (nextDo < nextDont && nextDo < nextMul) {

				isEnabled = true;
				index += doMatcher.end();
			} else if (nextDont < nextDo && nextDont < nextMul) {

				isEnabled = false;
				index += dontMatcher.end();
			} else if (nextMul < nextDo && nextMul < nextDont) {

				if (isEnabled) {
					result2 += getProduct(mulMatcher.group());
				}
				index += mulMatcher.end();
			} else {

				break;
			}
		}

		System.out.println("Result 2: " + result2);
	}

	private static int getProducts(String line) {
		Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
		Matcher matcher = null;
		int result = 0;
		matcher = pattern.matcher(line);
		if (matcher.find()) {
			do {
				result += getProduct(matcher.group());
			} while (matcher.find(matcher.start() + 1));
		}

		return result;
	}

	private static int getProduct(String match) {
		String[] numbersStr = match.replace("mul(", "").replace(")", "").split(",");
		return Integer.parseInt(numbersStr[0]) * Integer.parseInt(numbersStr[1]);
	}

}