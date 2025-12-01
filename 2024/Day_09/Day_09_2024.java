package Day_09;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Globals.ResourceLoader;

public class Day_09_2024 {
    public static void main(String[] args) {
        List<String> lines = ResourceLoader.getContentAsLines("2024", "Day_09" + File.separator + "Input.txt");
        String input = lines.get(0);

        List<Integer> fs = new ArrayList<>();
        boolean freeSpace = false;
        int id = 0;

        // Construct the initial file system representation
        for (char c : input.toCharArray()) {
            int count = Character.getNumericValue(c);
            for (int i = 0; i < count; i++) {
                if (freeSpace) {
                    fs.add(null); // Null represents free space
                } else {
                    fs.add(id);
                }
            }
            if (!freeSpace) id++;
            freeSpace = !freeSpace;
        }
        
        for (int i = 0; i < 100; i++) {
			System.out.print(fs.get(i) + ", ");
		}
        System.out.println();

        // Move blocks to compact the disk
        for (int i = fs.size() - 1; i >= 0; i--) {
            if (fs.get(i) == null) continue;
            int blockId = fs.get(i);
            fs.set(i, null);
            for (int j = 0; j < fs.size(); j++) {
                if (fs.get(j) == null) {
                    fs.set(j, blockId);
                    break;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
			System.out.print(fs.get(i) + ", ");
		}
        System.out.println();

        // Calculate checksum
        int checksum = 0;
        for (int i = 0; i < fs.size(); i++) {
            Integer blockId = fs.get(i);
            if (blockId != null) {
                checksum += i * blockId;
            }
        }

        System.out.println("Result 1: " + checksum);
    }
}
