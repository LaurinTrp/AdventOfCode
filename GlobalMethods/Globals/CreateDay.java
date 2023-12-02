package Globals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CreateDay {
	public static void main(String[] args) {
		
		try(Scanner s = new Scanner(System.in)){
			File f = new File("");

			int year = -1;
			String day = "";
			System.out.println("Which year?");
			if(s.hasNextInt()) {
				year = s.nextInt();
			}
			System.out.println("Which day?");
			if(s.hasNextInt()) {
				day = s.nextInt() + "";
				if(day.length() == 1) {
					day = "0" + day;
				}
			}
			
			if(year != -1 && !day.equals("")) {
				File srcFolder = new File(f.getAbsoluteFile(), year + "");
				if(srcFolder.exists()) {
					File folder;
					if((folder = new File(srcFolder, "Day_" + day)).mkdirs()) {
						new File(folder, "Day_" + day + "_" + year + ".java").createNewFile();
					}
				}else {
					throw new IOException("Source folder for this year does not exist");
				}
				
				File resFolder = new File(f.getAbsoluteFile(), "res" + File.separator + "Files");
				File folder;
				if((folder = new File(resFolder, "Day_" + day)).mkdirs()) {
					new File(folder, "Test.txt").createNewFile();
					new File(folder, "Input.txt").createNewFile();
				}else {
					throw new IOException("Resource folder does not exist");
				}
			}
			
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
