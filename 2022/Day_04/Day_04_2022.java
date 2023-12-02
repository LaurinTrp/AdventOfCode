package Day_04;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Loaders.ResourceLoader;

public class Day_04_2022 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2022", "Day_04" + File.separator + "Input.txt");
		
		int sum = 0;
		
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			
			String[] splitted = line.split("\\-|\\,");
			
			int lower0 = Integer.parseInt(splitted[0]);
			int upper0 = Integer.parseInt(splitted[1]);
			int lower1 = Integer.parseInt(splitted[2]);
			int upper1 = Integer.parseInt(splitted[3]);
			
			if(lower0 <= lower1 && lower1 <= upper0 || 
					lower1 <= lower0 && lower0 <= upper1) {
				sum++;
			}
		}
		
		System.out.println(sum);
	}
}
