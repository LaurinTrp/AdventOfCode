package Day_10;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.Model;
import com.microsoft.z3.Optimize;
import com.microsoft.z3.Status;
import com.microsoft.z3.Z3Exception;

import Globals.ResourceLoader;

public class Day_10_2025 {
	public static void main(String[] args) {

		long result1 = 0;
		long result2 = 0;
		List<String> lines = ResourceLoader.getContentAsLines("2025", "Day_10" + File.separator + "Input.txt");

		// Preparation
		int[] binaryLights = new int[lines.size()];
		int[] lightsLengths = new int[lines.size()];

		List<int[][]> buttonsInp = new ArrayList<>();
		int[][] targetJoltages = new int[lines.size()][];

		int[][] binaryButtons = new int[binaryLights.length][];

		Pattern lightsPattern = Pattern.compile("\\[(.*?)\\]");
		Pattern buttonPattern = Pattern.compile("\\((.*?)\\)");
		Pattern joltagePattern = Pattern.compile("\\{(.*?)\\}");

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			Matcher m = lightsPattern.matcher(line);
			if (m.find()) {
				String lights = m.group(1);
				lightsLengths[i] = lights.length();
				binaryLights[i] = lightToBinary(lights);
			}

			List<Integer> buttonsPt1 = new ArrayList<>();
			List<int[]> buttonsPt2 = new ArrayList<>();
			m = buttonPattern.matcher(line);
			while (m.find()) {
				String[] buttonsList = m.group(1).split(",");
				int[] buttonsArr = Arrays.stream(buttonsList).mapToInt(x -> Integer.parseInt(x)).toArray();
				buttonsPt1.add(buttonsArrToBinary(buttonsArr, lightsLengths[i]));
				buttonsPt2.add(buttonsArr);
			}
			binaryButtons[i] = buttonsPt1.stream().mapToInt(x -> x).toArray();
			buttonsInp.add(buttonsPt2.stream().toArray(int[][]::new));

			m = joltagePattern.matcher(line);
			if (m.find()) {
				String[] joltagesArr = m.group(1).split(",");
				targetJoltages[i] = Arrays.stream(joltagesArr).mapToInt(x -> Integer.parseInt(x)).toArray();
			}
		}

		// Calculation
		for (int i = 0; i < binaryLights.length; i++) {
			int target = binaryLights[i];
			int[] buttons = binaryButtons[i];

			int needed = findMinimumIterations(target, buttons);

			if (needed != -1) {
				result1 += needed;
			}
		}

		System.out.println("Result 1: " + result1);

		for (int i = 0; i < lines.size(); i++) {
			result2 += solveMachineZ3(buttonsInp.get(i), targetJoltages[i]);
		}

		System.out.println("Result 2: " + result2);
	}

	public static int lightToBinary(String s) {
		int value = 0;
		for (char c : s.toCharArray()) {
			value <<= 1;
			if (c == '#')
				value |= 1;
		}
		return value;
	}

	public static int buttonsArrToBinary(int[] buttonsArr, int lightLength) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lightLength; i++) {
			sb.append("0");
		}

		for (int i = 0; i < buttonsArr.length; i++) {
			sb.setCharAt(buttonsArr[i], '1');
		}

		return Integer.parseInt(sb.toString(), 2);
	}

	public static List<Integer> createMasks(int[] buttonsArr, int k) {
		List<Integer> masks = new ArrayList<>();
		generate(buttonsArr, k, 0, 0, 0, masks);
		return masks;
	}

	private static void generate(int[] arr, int k, int start, int depth, int currentMask, List<Integer> result) {

		if (depth == k) {
			result.add(currentMask);
			return;
		}

		for (int i = start; i < arr.length; i++) {
			generate(arr, k, i + 1, depth + 1, currentMask ^ arr[i], result);
		}
	}

	public static int findMinimumIterations(int target, int[] buttonsArr) {
		for (int k = 1; k <= buttonsArr.length; k++) {
			List<Integer> masks = createMasks(buttonsArr, k);

			for (int mask : masks) {
				if (mask == target) {
					return k; // FOUND: smallest k
				}
			}
		}
		return -1; // no match
	}

	public static int solveMachineZ3(int[][] buttons, int[] target) throws Z3Exception {
		Context ctx = new Context();

		int m = target.length;
		int n = buttons.length;

		// Create integer variables x_0 .. x_(n-1)
		IntExpr[] x = new IntExpr[n];
		for (int j = 0; j < n; j++) {
			x[j] = ctx.mkIntConst("x_" + j);
		}

		Optimize opt = ctx.mkOptimize();

		// x[j] >= 0
		for (int j = 0; j < n; j++) {
			opt.Add(ctx.mkGe(x[j], ctx.mkInt(0)));
		}

		// Build A matrix implicitly & add equality constraints
		for (int i = 0; i < m; i++) {
			ArithExpr sum = ctx.mkInt(0);

			for (int j = 0; j < n; j++) {
				boolean affects = false;
				for (int idx : buttons[j]) {
					if (idx == i)
						affects = true;
				}

				if (affects) {
					sum = ctx.mkAdd(sum, x[j]);
				}
			}

			opt.Add(ctx.mkEq(sum, ctx.mkInt(target[i])));
		}

		// Objective: minimize sum(x_j)
		ArithExpr obj = ctx.mkInt(0);
		for (int j = 0; j < n; j++) {
			obj = ctx.mkAdd(obj, x[j]);
		}

		opt.MkMinimize(obj);

		if (opt.Check() != Status.SATISFIABLE) {
			return -1; // impossible
		}

		Model model = opt.getModel();
		int total = 0;
		for (int j = 0; j < n; j++) {
			total += Integer.parseInt(model.evaluate(x[j], false).toString());
		}

		return total;
	}
}
