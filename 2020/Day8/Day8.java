package Day8;

import java.util.ArrayList;

import Globals.FileReader;

public class Day8 {
	static ArrayList<String> string = FileReader.getFileContent("Day8\\Instructions"), linesDone = new ArrayList<>();
	static ArrayList<String[]> stringArray = new ArrayList<>(), newStringArray = new ArrayList<>();
	static int acc = 0, current = 0;
	static ArrayList<Integer> ints = new ArrayList<>(), intsJmp = new ArrayList<>();

	public static void main(String[] args) {

		for (String strings : string) {
			stringArray.add(strings.split(" "));
		}
		while (true) {
			if(ints.contains(current)) {
				System.err.println("First Part: " + acc);
				break;
			}
			ints.add(current);
			current += action(stringArray.get(current));
		}
		
		for (int i = 0; i < stringArray.size(); i++) {
			if(stringArray.get(i)[0].equals("jmp")) {
				intsJmp.add(i);
			}
		}
		for (int i = 0; i < intsJmp.size(); i++) {
			newStringArray.clear();
			newStringArray.addAll(stringArray);
			ints.clear();
			acc = 0;
			current = 0;
			newStringArray.set(intsJmp.get(i), new String[] {"nop", newStringArray.get(i)[1]});
			while (true) {
				if(ints.contains(current)) {
					break;
				}
				ints.add(current);
				try {
					current += action(newStringArray.get(current));
				} catch (Exception e) {
					System.err.println("Second Part: "+ acc);
				}
			}
		}

	}

	private static int action(String[] str) {
		if (str[0].equals("jmp")) {
			int jump = Integer.parseInt(str[1]);
			return jump;
		} else if (str[0].equals("acc")) {
			acc += Integer.parseInt(str[1]);
			return 1;
		} else if (str[0].equals("nop")) {
			return 1;
		}
		return 0;
	}

}
