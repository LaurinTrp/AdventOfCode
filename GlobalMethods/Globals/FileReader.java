package Globals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

	public static String getFileContentAsString(String folder, String filename) {
		try (InputStream is = new FileInputStream(new File(folder + filename));) {
			String s = new String(is.readAllBytes());
			return s;
		} catch (IOException e) {
			return null;
		}
	}

	public static ArrayList<String> getFileContent(String path) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new java.io.FileReader(
					new File("E:\\Programmieren\\Java\\Git\\AdventOfCode\\AdventOfCode\\2020\\" + path)));
			ArrayList<String> strings = new ArrayList<>();
			String line = reader.readLine();
			while (line != null) {
				strings.add(line);
				line = reader.readLine();
			}
			return strings;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getFileContent(String folder, String filename) {
		String content = getFileContentAsString(folder, filename);
		List<String> strings = content.lines().collect(Collectors.toList());
		return strings;
	}

}
