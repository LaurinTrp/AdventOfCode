 package Day_08;

 import java.io.File;
 import java.util.List;
 import Globals.ResourceLoader;

 public class Day_08_2024 {
     public static void main(String[] args) {
         int result1 = 0;
         int result2 = 0;
         List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_08" + File.separator + "Test.txt");


System.out.println("Result 1: " + result1);
System.out.println("Result 2: " + result2);
     }
 }
