package Day03;

import java.awt.Point;
import java.util.ArrayList;

public class Day3 {
	public static void main(String[] args) {

		Point p = new Point(0, 0);
		Point[] points = { new Point(1, 1), new Point(3, 1), new Point(5, 1), new Point(7, 1), new Point(1, 2) };
		int counter = 0;
		ArrayList<Integer> product = new ArrayList<>();
		ArrayList<String[]> lines = new ArrayList<>();
		ArrayList<String> strings = new ArrayList<>();
		strings = Globals.FileReader.getFileContent("Day3\\Forrest");
		for (String string : strings) {
			lines.add(string.split(""));
		}
		for (int j = 0; j < points.length; j++) {
			p.setLocation(0, 0);
			counter = 0;
			for (int i = 0; i < lines.size(); i++) {
				try {
					if (lines.get(p.y)[p.x].equals("#")) {
						counter++;
					}

					p.setLocation((p.getX() + points[j].x) % lines.get(i).length, p.getY() + points[j].y);
				} catch (Exception e) {
				}
			}
			product.add(counter);
		}
		for (int i = 1; i < product.size(); i++) {
			product.set(0, product.get(0) * product.get(i));
		}
		System.out.println(product.get(0));
	}

}
