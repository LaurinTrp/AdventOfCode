package Day_05;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Globals.ResourceLoader;

public class Day_05_2025 {
	public static void main(String[] args) {
		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_05" + File.separator + "Input.txt");

		List<IdRange> idRanges = new ArrayList<>();

		int counter = 0;
		while (!lines.get(counter).isEmpty()) {
			String[] ranges = lines.get(counter).split("-");
			idRanges.add(new IdRange(Long.parseLong(ranges[0]), Long.parseLong(ranges[1])));
			counter++;
		}

		for (int i = counter + 1; i < lines.size(); i++) {
			long id = Long.parseLong(lines.get(i));
			for (IdRange r : idRanges) {
				if (r.isInRange(id)) {
					result1++;
					break;
				}
			}
		}

		System.out.println("Result 1: " + result1);

		List<IdRange> realRanges = mergeRanges(idRanges);

		for (IdRange r : realRanges) {
			result2 += (r.end - r.start + 1);
		}

		System.out.println("Result 2: " + result2);
	}

	public static List<IdRange> mergeRanges(Collection<IdRange> ranges) {
		if (ranges.isEmpty())
			return Collections.emptyList();

		// Sort by start
		List<IdRange> sorted = new ArrayList<>(ranges);
		sorted.sort(Comparator.comparingLong(r -> r.start));

		List<IdRange> result = new ArrayList<>();
		IdRange current = sorted.get(0);

		for (int i = 1; i < sorted.size(); i++) {
			IdRange next = sorted.get(i);

			// Overlapping or touching?
			if (next.start <= current.end + 1) {
				current = new IdRange(Math.min(current.start, next.start), Math.max(current.end, next.end));
			} else {
				result.add(current);
				current = next;
			}
		}

		result.add(current);
		return result;
	}

	public static class IdRange {
		long start, end;

		public IdRange(long start, long end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return start + " " + end;
		}

		public boolean isInRange(long value) {
			return value >= start && value <= end;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return ((IdRange) obj).start == this.start && ((IdRange) obj).end == this.end;
		}

	}
}
