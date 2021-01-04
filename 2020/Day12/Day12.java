package Day12;

import java.util.ArrayList;

import Globals.FileReader;

public class Day12 {

	static ArrayList<String> strings = FileReader.getFileContent("Day12\\Movement");

	static int north, east, rotation;
	static char direction = 'e';
	static char[] directions = "nesw".toCharArray();

	public static void main(String[] args) {

		for (String string : strings) {
			switch (string.charAt(0)) {
			case 'F': {
				front(Integer.parseInt(string.substring(1)));
				break;
			}
			case 'N': {
				north += Integer.parseInt(string.substring(1));
				break;
			}
			case 'S': {
				north -= Integer.parseInt(string.substring(1));
				break;
			}
			case 'E': {
				east += Integer.parseInt(string.substring(1));
				break;
			}
			case 'W': {
				east -= Integer.parseInt(string.substring(1));
				break;
			}
			case 'L': {
				rotation(Integer.parseInt(string.substring(1)), 'L');
				break;
			}
			case 'R': {
				rotation(Integer.parseInt(string.substring(1)), 'R');
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + string.charAt(0));
			}
		}
		
		System.out.println(north + east);

	}
	
	private static void rotation(int value, char rotation) {
		int pos = 0;
		for (int i = 0; i < directions.length; i++) {
			if(direction == directions[i]) {
				pos = i;
				break;
			}
		}
		if(rotation == 'L') {
			value = -value;
		}
		direction = directions[ Math.abs(pos +(value/90)) % 4];
	}

	private static void front(int value) {
		switch (direction) {
		case 'e': {
			east += value;
			break;
		}
		case 'w': {
			east -= value;
			break;
		}
		case 'n': {
			north += value;
			break;
		}
		case 's': {
			north -= value;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}

}
