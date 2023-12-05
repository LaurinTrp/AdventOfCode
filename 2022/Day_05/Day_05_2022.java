package Day_05;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Globals.ResourceLoader;

public class Day_05_2022 {

	public static void main(String[] args) {
		List<String> lines = ResourceLoader.getContentAsLines("2022", "Day_05" + File.separator + "Input.txt");

		Map<Integer, List<Character>> stacks = new TreeMap<>();

		for (String line : lines) {
			if (line.contains("[")) {
				for (int i = line.indexOf('[') + 1; i < line.length(); i += 4) {
					
					if (line.charAt(i) != ' ') {
						if (stacks.containsKey(i)) {
							stacks.get(i).add(line.charAt(i));
						} else {
							List<Character> list = new ArrayList<>();
							list.add(line.charAt(i));
							stacks.put(i, list);
						}
					
					}
					
				}
			}else{
				break;
			}
		}

		Map<Integer, List<Character>> stacksSorted = new TreeMap<>();
		for(int i = 0; i < stacks.size(); i++) {
			stacksSorted.put((i+1), stacks.get(stacks.keySet().toArray()[i]));
		}
		
		for(int i = 0; i < lines.size(); i++) {
			if(lines.get(i).matches("move \\d from \\d to \\d")) {
				String line = lines.get(i).replaceAll("\\s", "");
				String[] splitted = line.split("move|from|to");
				String[] splittedFixed = Arrays.copyOfRange(splitted, 1, splitted.length);

				List<Character> from = stacksSorted.get(Integer.parseInt(splittedFixed[1]));
				List<Character> to = stacksSorted.get(Integer.parseInt(splittedFixed[2]));
				
				for (int j = 0; j < Integer.parseInt(splittedFixed[0]); j++) {
					to.add(0, from.get(0));
					from.remove(0);
				}
				
			}
		}
		
		for (Integer i : stacksSorted.keySet()) {
			System.out.print(stacksSorted.get(i).get(0));
		}
		

	}

}