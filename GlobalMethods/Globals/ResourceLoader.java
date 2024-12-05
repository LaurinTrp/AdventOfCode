package Globals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.xml.sax.InputSource;

public class ResourceLoader {

	public static String getFileContentAsString(String folder, String filename) {
		File file = new File("res" + File.separator + "Files" + File.separator + folder + File.separator + filename);
		try (InputStream is = new FileInputStream(file);) {
			String s = new String(is.readAllBytes());
			return s;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<String> getContentAsLines(String folder, String filename){
		String content = getFileContentAsString(folder, filename);
		return content.lines().collect(Collectors.toList());
		
	}
}
