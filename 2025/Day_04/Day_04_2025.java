 package Day_04;

 import java.io.File;
 import java.util.List;
 import Globals.ResourceLoader;

 public class Day_04_2025 {
     public static void main(String[] args) {
         int result1 = 0;
         int result2 = 0;
         List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_04" + File.separator + "Test.txt");


System.out.println("Result 1: " + result1);
System.out.println("Result 2: " + result2);
     }
 }
