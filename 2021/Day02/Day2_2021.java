package Day02;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Globals.FileReader;

public class Day2_2021 {
	public static void main(String[] args) {
		List<String> lines = FileReader.getFileContent(Paths.get("").toAbsolutePath().toString(),
				"/2021/Day2/input.txt");
		int horizontal = 0, vertical = 0, aim = 0, depth = 0;
		for (String line : lines) {
			String[] split = line.split(" ");
			int value = Integer.parseInt(split[1]);
			switch (split[0]) {
				case "forward":
					horizontal += value;
					depth += aim*value;
					break;
				case "down":
					vertical+=value;
					aim += value;
					break;
				case "up":
					vertical-=value;
					aim -= value;
					break;
			}
		}
		System.out.println(horizontal * vertical);
		System.out.println(horizontal * depth);
	}
}
