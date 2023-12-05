package Day_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Globals.ResourceLoader;

public class Day_02_2_2022 {
	public static void main(String[] args) throws Exception {
		
		List<String> lines = ResourceLoader.getContentAsLines("2022", "Day_02/Input.txt");
		
		int sum = 0;
		
		for (String line : lines) {
			char opponent = line.charAt(0);
			char outcome = line.charAt(2);
			
			int choice = choice(opponent, outcome);
			if(choice == -1)throw new Exception("-1 als Ergebnis");
			sum+=choice;
		}
		
		System.out.println(sum);
	}
	
	public static int choice(char opponent, char outcome) {
		if(opponent == 'A') {
			if(outcome=='X')return 3 + 0;
			if(outcome=='Y')return 1 + 3;
			if(outcome=='Z')return 2 + 6;
		}
		if(opponent == 'B') {
			if(outcome=='X')return 1 + 0;
			if(outcome=='Y')return 2 + 3;
			if(outcome=='Z')return 3 + 6;
		}
		if(opponent == 'C') {
			if(outcome=='X')return 2 + 0;
			if(outcome=='Y')return 3 + 3;
			if(outcome=='Z')return 1 + 6;
		}
		return -1;
	}
	
}
