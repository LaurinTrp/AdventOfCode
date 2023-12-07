package Globals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
						File srcFile = new File(folder, "Day_" + day + ".java");
						srcFile.createNewFile();
						writeTemplate(srcFile, year, day);
					}
				}else {
					throw new IOException("Source folder for this year does not exist");
				}
				
				File resFolder = new File(f.getAbsoluteFile(), "res" + File.separator + "Files" + File.separator + year);
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
	
	
	private static void writeTemplate(File file, int year, String day) {
		try(FileWriter fw = new FileWriter(file);
				BufferedWriter writer = new BufferedWriter(fw);){
			StringBuilder sb = new StringBuilder();
			sb.append("package Day_" + day + ";");
			sb.append("\n");
			sb.append("import java.io.File;\nimport java.util.List;\nimport Globals.ResourceLoader;");
			sb.append("\n\n");
			sb.append("public class Day_" + day + " {\n");
			sb.append("\tpublic static void main(String[] args){\n");
			sb.append("\t\tList<String> lines = ResourceLoader.getContentAsLines(\"" + year + "\", \"Day_" + day + "\" + File.separator + \"Input.txt\");\n");
			sb.append("\t}\n");
			sb.append("}");
			writer.write(sb.toString());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
