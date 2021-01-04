package Day4;

import java.util.ArrayList;

public class Day4 {
	public static void main(String[] args) {

		try {
			ArrayList<String> strings = Globals.FileReader.getFileContent("Day4\\Passports"), passport = new ArrayList<>();
			ArrayList<String[]> validPassport = new ArrayList<>();
			String lines = "";

			String[] fields = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };


			for (int i = 0; i < strings.size(); i++) {
				lines = "";
				if (strings.get(i).equals("")) {
					for (int j = i - 1; j > 0; j--) {
						lines += strings.get(j) + " ";
						if (strings.get(j).equals("")) {
							break;
						}
					}
				}
				if (i == strings.size() - 1) {
					for (int j = i; j > 0; j--) {
						lines += strings.get(j) + " ";
						if (strings.get(j).equals("")) {
							break;
						}
					}
				}
				if (!lines.equals("")) {
					passport.add(lines);
				}
			}
			passport.set(0, passport.get(0) + strings.get(0));
			int counter = 0, counterFields = 0;
			for (int i = 0; i < passport.size(); i++) {
				counterFields = 0;
				for (int j = 0; j < fields.length; j++) {
					if (passport.get(i).contains(fields[j])) {
						counterFields++;
					}
				}
				if (counterFields == 7 || counterFields == 8) {
					counter++;
					validPassport.add(passport.get(i).split(" "));
				}
			}
			System.out.println("Part 1: " + counter);
			
			
			counter = 0;
			int[] lower = { 1920, 2010, 2020 }, upper = { 2002, 2020, 2030 };
			String[] hairColors = "amb blu brn gry grn hzl oth".split(" ");
			ArrayList<String[]> validPassport2 = new ArrayList<>();
			int value, counterValid = 0;
			for (int i = 0; i < validPassport.size(); i++) {
				counterValid = 0;
				for (int j = 0; j < validPassport.get(i).length; j++) {
					for (int j2 = 0; j2 < 3; j2++) {
						if (validPassport.get(i)[j].contains(fields[j2])) {
							value = Integer.parseInt(validPassport.get(i)[j].split(":")[1]);
							if (value >= lower[j2] && value <= upper[j2]) {
								validPassport2.add(validPassport.get(i));
								counterValid++;
							}
						}
					}
					if (validPassport.get(i)[j].contains(fields[3])) {
						if (validPassport.get(i)[j].split(":")[1].contains("cm")) {
							value = Integer.parseInt(validPassport.get(i)[j].split(":")[1].replaceAll("cm", ""));
							if (value >= 150 && value <= 193) {
								validPassport2.add(validPassport.get(i));
								counterValid++;
							}
						} else if (validPassport.get(i)[j].split(":")[1].contains("in")) {
							value = Integer.parseInt(validPassport.get(i)[j].split(":")[1].replaceAll("in", ""));
							if (value >= 59 && value <= 76) {
								validPassport2.add(validPassport.get(i));
								counterValid++;
							}
						}
					}

					if (validPassport.get(i)[j].contains(fields[4])
							&& validPassport.get(i)[j].split(":")[1].contains("#")
							&& validPassport.get(i)[j].split(":")[1].length() == 7) {
						counterValid++;
					}

					if (validPassport.get(i)[j].contains(fields[5])) {
						for (int k = 0; k < hairColors.length; k++) {
							if (validPassport.get(i)[j].split(":")[1].contains(hairColors[k])) {
								counterValid++;
								break;
							}
						}
					}
					if (validPassport.get(i)[j].contains(fields[6])) {
						if (validPassport.get(i)[j].split(":")[1].length() == 9) {
							counterValid++;
						}
					}
				}
				if(counterValid == 7) {
					counter++;
				}
			}

			System.out.println("Part 2: " + counter);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
