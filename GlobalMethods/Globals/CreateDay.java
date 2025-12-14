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
						File srcFile = new File(folder, "Day_" + day  + "_" + year + ".java");
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
			String packageName = "Day_" + day;
			String className = "Day_" + day + "_" + year;
			String content = """
				    package %s;

				    import java.io.File;
				    import java.util.List;
				    import Globals.ResourceLoader;

				    public class %s {
				        public static void main() {
				            long result1 = 0;
				            long result2 = 0;
				            List<String> lines = ResourceLoader.getContentAsLines("%s", "Day_%s" + File.separator + "Test.txt");
				            
				            
							System.out.println("Result 1: " + result1);
							System.out.println("Result 2: " + result2);
				        }
				    }
				    """.formatted(packageName, className, year, day);
			writer.write(content);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
