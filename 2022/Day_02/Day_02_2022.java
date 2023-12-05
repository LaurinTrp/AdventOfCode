package Day_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Globals.ResourceLoader;

public class Day_02_2022 {
	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2022", "Day_02/Input.txt");
		HashMap<Character, Integer> points = new HashMap<>();
		points.put('X', 1);
		points.put('Y', 2);
		points.put('Z', 3);
		
		int sum = 0;
		
		for (String line : lines) {
			char opponent = line.charAt(0);
			char player = line.charAt(2);
			
			sum = sum + points.get(player) + winner(opponent, player);
		}
		
		System.out.println(sum);
		
	}
	
	public static int winner(char opponent, char player) {
		if(opponent == 'A') {
			if(player == 'X')return 3;
			if(player == 'Y')return 6;
			if(player == 'Z')return 0;
		}
		if(opponent == 'B') {
			if(player == 'X')return 0;
			if(player == 'Y')return 3;
			if(player == 'Z')return 6;
		}
		if(opponent == 'C') {
			if(player == 'X')return 6;
			if(player == 'Y')return 0;
			if(player == 'Z')return 3;
		}
		return -1;
		
	}
	
}
