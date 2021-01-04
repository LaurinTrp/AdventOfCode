package Day10;

import java.util.ArrayList;
import java.util.Collections;

import Globals.FileReader;

public class Day10 {
	public static void main(String[] args) {
		ArrayList<String> strings = FileReader.getFileContent("Day10\\Adapters");
		ArrayList<Integer> ints = new ArrayList<>();
		int oneJump = 0, threeJumps = 0;
		
		ints.add(0);
		
		for (String string : strings) {
			ints.add(Integer.parseInt(string));
		}
		
		
		Collections.sort(ints);
		ints.add(ints.get(ints.size() - 1)+3);
		
		System.out.println(ints);
		
		for (int i = 0; i < ints.size() - 1; i++) {
			if(ints.get(i) == ints.get(i + 1) - 1) {
				oneJump++;
			}else if(ints.get(i) == ints.get(i + 1) - 2) {
			}else if(ints.get(i) == ints.get(i + 1) - 3) {
				threeJumps++;
			}else {
				System.err.println("LOOOOOOOOOOL");
			}
		}
		System.err.println(oneJump + "\t" + threeJumps + "\t" + (oneJump * threeJumps));
	}
}
