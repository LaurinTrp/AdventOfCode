package Day_03;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Globals.ResourceLoader;

public class Day_03 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2023", "Day_03" + File.separator + "Test2.txt");
		partOne(lines);
	}

	private static void partOne(List<String> lines) {
		Pattern pattern = Pattern.compile("\\d{1,}");
		int result = 0;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				String match = matcher.group();
				if(match((i < 1) ? null : lines.get(i - 1), line, (i > lines.size() - 2) ? null : lines.get(i + 1), match)) {
					result += Integer.parseInt(match);
				}
			}
		}
		System.out.println(result);
	}

	private static boolean match(String prev, String line, String past, String match) {
		String pattern = "[.]*[^.|\\d][.]*";

		int index = Math.max(0, line.indexOf(match) - 1);
		int end = Math.min(line.length(), match.length() + index + 2);
		if (prev != null) {
			String substring = prev.substring(index, end);
			if (substring.matches(pattern)) {
				return true;
			}
		}
		if (past != null) {
			String substring = past.substring(index, end);
			if (substring.matches(pattern)) {
				return true;
			}
		}
		int indexBefore = line.indexOf(match) - 1;
		int indexAfter = indexBefore + 1 + match.length();
		return ((indexBefore != -1 && line.charAt(indexBefore) != '.') || (indexAfter != line.length() && line.charAt(indexAfter) != '.'));
	}
}