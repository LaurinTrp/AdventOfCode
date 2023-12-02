package Loaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.xml.sax.InputSource;

public class ResourceLoader {
	public static ArrayList<String> getContentAsLines(String folder, String filename){
		
		ArrayList<String> output = new ArrayList<>();
		
		File file = new File("res" + File.separator + "Files" + File.separator + folder + File.separator + filename);
		try(FileInputStream fis = new FileInputStream(file);
				Scanner scanner = new Scanner(fis)){
			while(scanner.hasNextLine()) {
				output.add(scanner.nextLine());
			}
			
			return output;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
