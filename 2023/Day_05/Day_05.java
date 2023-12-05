package Day_05;
import java.io.File;
import java.util.List;

import Globals.ResourceLoader;

public class Day_05 {
	public static void main(String[] args){
		String content = ResourceLoader.getFileContentAsString("2023", "Day_05" + File.separator + "Test.txt");
	}
	
	private static void partOne(String content) {
		String[] splitted = content.split("\n\n");
		System.out.println(splitted);
	}
}